<?php

namespace App\Http\Controllers;

use App\Enums\RoleEnum;
use App\Http\Controllers\Controller;
use App\Http\Services\AuthService;
use App\Models\Coupon;
use App\Models\Point;
use App\Models\Service;
use App\Models\Shop;
use App\Models\Transaction;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Http\Response;
use Illuminate\Support\Facades\DB;
use Ramsey\Collection\Collection;

/**
 * @tags Người dùng
 */
class UserController extends Controller
{

    public function index(){
        # TODO: Admin only
        return User::all();
    }

    /**
     * 1. Thông tin người dùng hiện tại
     *
     * Hiển thị vai trò, shop sở hữu (đối với manager), mã qr (đối với user)
     *
     */
    public function show(Request $request){
        // Trả về thông tin của user hiện tại
        if ($request->user->role == RoleEnum::User){
            $request->user->qr = $request->user->id;
            unset($request->user->points);
            unset($request->user->coupons);
        }
        else if ($request->user->role == RoleEnum::ShopOwner){
            $request->user->load('shop');
        }

        return $request->user;
    }

    /**
     * 2. Lấy các cửa hàng gợi ý cho người dùng (có thể dựa theo vị trí hoặc cửa hàng mới)
     *
     * Hiển thị danh sách cửa hàng ở gần người dùng, nếu không có thì sẽ hiện cửa hàng mới
     *
     */
    public function getRecommendedShop(Request $request){
        $validate = $request->validate([
            'limit' => 'nullable|integer|min:0',
            'lat' => 'nullable|numeric',
            'long' => 'nullable|numeric',
        ]);

        if (!$request->has('limit')){
            $validate['limit'] = 10;
        }

        if ($request->has('lat') && $request->has('long')) {
            $shops = Shop::where('location', 'near', [
                '$geometry' => [
                    'type' => 'Point',
                    'coordinates' => [
                        $validate['long'],
                        $validate['lat']
                    ]
                ],
                '$maxDistance' => 10000
            ])->limit($validate['limit'])->get();

            if ($shops->count() < $validate['limit']){
                $shops = $shops->merge(Shop::orderBy('created_at', 'desc')->limit($validate['limit'] - $shops->count())->get());
            }
        }
        else {
            $shops = Shop::orderBy('created_at', 'desc')->limit($validate['limit'])->get();
        }
        return $shops;
    }

    /**
     * 4. Lấy danh sách điểm các cửa hàng của người dùng
     *
     * Hiển thị danh sách điểm các cửa hàng mà người dùng đã ghé thăm và đã tích điểm
     *
     */

    public function getListPoint(Request $request){
        $request->user->points->load('shop')->sortByDesc('updated_at');
        return $request->user->points->all();
    }

    /**
     * 3. Lấy danh sách cửa hàng đã ghé thăm của người dùng
     *
     * Hiển thị danh sách cửa hàng người dùng đã ghé thăm và tích điểm
     *
     */
    public function getVisitedShop(Request $request){
        $validate = $request->validate([
            'limit' => 'nullable|integer|min:0',
        ]);

        if (!$request->has('limit')){
            $validate['limit'] = 10;
        }

        $request->user->load('points.shop');
        $shops = $request->user->points->sortByDesc('updated_at')->take($validate['limit'])->pluck('shop')->unique('_id');
        return $shops;
    }

    /**
     * 5. Lấy danh sách ưu đãi có thể đổi được của người dùng với các cửa hàng
     *
     * Trả về list các ưu đãi nhỏ hơn hoặc bằng điểm hiện tại của user đối với shop đó
     *
     */
    public function getAvailableCoupons(Request $request)
    {
        $request->user->points->load(['shop', 'shop.coupons']);
        $coupons = collect();
        $points = $request->user->points->sortByDesc('updated_at')->unique('shop_id');
        foreach ($points as $point) {
            if (!$point->shop) continue;
            $result = $point->shop->coupons->where('require_point', $point->points)->map(function($coupon) use ($point){
                unset($point->shop->coupons);
                $coupon->shop = $point->shop;
                return $coupon;
            });
            $coupons = $coupons->concat($result->all());
        }
        return $coupons;
    }

    /**
     * 10. Đổi điểm thành ưu đãi
     *
     * Lấy coupon của shop và tạo coupon mới cho user
     *
     */
    public function exchangeCoupon(Request $request){
        $request->validate([
            'coupon_id' => 'required|string'
        ]);

        $coupon = Coupon::find($request->input('coupon_id'));
        if(!$coupon){
            return Response('Không tìm thấy mã ưu đãi!', 404);
        }

        $shop_point = $request->user->points()->where('shop_id', $coupon->shop_id)->first();
        if(!$shop_point){
            return Response('Bạn không có điểm của cửa hàng này!', 404);
        }

        if($shop_point->points < $coupon->require_point){
            return Response('Bạn không đủ điểm để đổi mã ưu đãi này!', 404);
        }

        DB::beginTransaction();
        try {
            $shop_point->points -= $coupon->require_point;
            $shop_point->save();

            // copy coupon
            $new_coupon = $coupon->replicate()->fill([
                'expired_at' => now()->addMonths($coupon->expired_after)->toDateTimeString(),
                'redeemed_at' => null
            ]);

            // Tạo coupon cho user
            $request->user->coupons()->save($new_coupon);

            // Link coupon to shop
            $new_coupon->shop()->associate($coupon->shop);

            Transaction::create([
                'user_id' => $request->user->_id,
                'coupon_id' => $coupon->_id,
                'type' => 'minus',
                'point' => $coupon->require_point,
                'current_point' => $shop_point->points,
                'reason' => 'Đổi quà '.$coupon->name
            ]);

            DB::commit();
        } catch (\Exception $e) {
            DB::rollBack();
            return Response('Đổi mã ưu đãi thất bại!', 404);
        }
        return Response('Đổi mã ưu đãi thành công!', 200);
    }

    /**
     * 11. Lấy danh sách ưu đãi của bản thân
     *
     * Trả về list các coupon mà user đã đổi
     *
     */
    public function getCoupons(Request $request){
        $request->user->coupons->load('shop');
        return $request->user->coupons;
    }

    public function store(Request $request){
        /**
         * Validate các thuôc tính của user từ request
         */
        $validate = $request->validate([
            'auth_id' => 'required|string', // Firebase Auth
            'name' =>'required|string',
            'role' =>'in:user,manager,admin'
        ]);

        /**
         * Nếu không nhận đc uid sau khi sign up ở login thì yêu cầu để nhận uid
         * ở đoạn này
         */

        $auth = new AuthService();
        $uid = $auth->validateIdToken($request->header('Authorization', ''));
        if(!$uid){
            return Response("Token không hợp lệ!", 404);
        }

        /**
         * Nếu nhận đc uid sau khi tạo thì dùng nó để bắt đầu tạo user
        */
        // $uid = $request->input('uid');

        $user = new User($validate);
        $user->_id = $uid;
        return $user->save();

    }

    public function update(Request $request){

        /**
         * Validate các thuôc tính của user từ request
         */
        $request->validate([
            'name' =>'required|string',
            'role' =>'in:user,manager,admin'
        ]);

        $auth = new AuthService();
        $uid = $auth->validateIdToken($request->header('Authorization', ''));
        if(!$uid){
            return Response("Token không hợp lệ!", 404);
        }

        $user = User::find($uid);
        if(!$user){
            return Response("User không tồn tại!", 404);
        }
        $user->name = $request->input('name');
        $user->role = $request->input('role');
        return $user->save();
    }

    public function updatePoint(User $user, Service $service = null, Coupon $coupon = null){
        $user_point = $user->points;

        //Kiểm tra service và coupon
        if(!$service){
            foreach($user_point as $point){
                if($point->shop->coupons->contains($coupon)){
                    $current_point = $point->points;
                    if($current_point >= $coupon->require_point){
                        echo "in khuyến mãi";
                        $point->points = $current_point - $coupon->require_point;
                        $point->save();
                        return ["success", "khuyến mãi"];
                    }else{
                        return ["fail", "khuyến mãi"];
                    }
                }
            }
            return ["fail", "khuyến mãi"];
        }else{
            foreach($user_point as $point){
                if($point->shop->services->contains($service)){
                    echo "in dịch vụ";
                    $point->points += $service->points_reward;
                    $point->save();
                    return ["success", "services"];
                }
            }

            //Nếu khách hàng chưa có điểm ở shop đó thì sẽ add new record
            $shop = $service->shop;
            $point = new Point(['points' => $service->points_reward]);
            $point->user()->associate($user);
            $point->shop()->associate($shop);
            $point->save();
            return ["success", "services"];
        }

        return ["fail", "all"];
    }

    public function getPointByShopId(Request $request, $shopId){
        // $auth = new AuthService();
        // $uid = $auth->validateIdToken($request->header('Authorization', ''));
        // if(!$uid){
        //     return Response("Token không hợp lệ!", 404);
        // }
        $user = User::find('DlFsK22Vk0Ml5UluYzFvcV1x1AE3');
        $user_point = $user->user_point;
        echo $user_point;
        foreach($user_point as $shop){
            // if($shop->_id == $shopId){
            //     echo $shop->pivot;
            // }
            echo $shop->user_points;
        }
    }
}
