<?php
namespace App\Http\Controllers;

use App\Enums\RoleEnum;
use App\Http\Services\AuthService;
use App\Models\Coupon;
use App\Models\Shop;
use App\Models\Transaction;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Symfony\Component\HttpFoundation\Response;

/**
 * @tags Cửa hàng (Shop)
 */
class ShopController extends Controller{
    // TODO: API thứ 2 - Đề xuất cho user các shop gần nhất
    public function index(){
        return Shop::all();
    }

    /**
     * 16 - Xem. Xem shop cho manager
     *
     * Thực hiện xem shop
     *
     */
    public function show(Request $request){
        if (!$request->user->shop){
            return Response("User chưa có shop!", 400);
        }
        return $request->user->shop;
    }

    /**
     * 16 - Tạo. Tạo shop cho manager
     *
     * Thực hiện tạo shop với các thuộc tính như tên, mô tả, địa chỉ, điện thoại, logo, cover, cách tính điểm (point trigger), vĩ độ, kinh độ
     *
     */
    public function store(Request $request){
        if ($request->user->shop){
            return Response("User đã có shop!", 400);
        }

        $validate = $request->validate([
            'name' => 'required|string',
            'description' => 'nullable|string',
            'address' => 'required|string',
            'phone' => 'nullable|regex:/(0)[0-9]{9}/',
            'logo' => 'required|file|image|max:20000',
            'cover' => 'nullable|file|image|max:20000',
            'point_trigger' => 'nullable|string',
            'latitude' => 'nullable|numeric',
            'longitude' => 'nullable|numeric'
        ]);

        //Lưu file logo và cover vào storage
        if ($request->hasFile('logo')) {
            $logo_path = cloudinary()->upload($request->file('logo')->getRealPath())->getSecurePath();
            $validate['logo'] = $logo_path;
        }

        if ($request->hasFile('cover')) {
            $cover_path = cloudinary()->upload($request->file('cover')->getRealPath())->getSecurePath();
            $validate['cover'] = $cover_path;
        }

        $shop = new Shop($validate);

        //Có thể dùng 1 trong 2 cách bên dưới để lưu shop có relate to user
        $request->user->shop()->save($shop);

        return Response("Tạo shop thành công!", 200);
    }

    /**
     * 16 - Sửa. Sửa shop cho manager
     *
     * Thực hiện sửa shop với các thuộc tính như tên, mô tả, địa chỉ, điện thoại, logo, cover, cách tính điểm (point trigger), vĩ độ, kinh độ
     *
     */
    public function update(Request $request){
        $shop = $request->user->shop;
        if (!$shop){
            return Response("Không tìm thấy shop!", 404);
        }

        $validate = $request->validate([
            'name' => 'required|string',
            'description' => 'nullable|string',
            'address' => 'required|string',
            'phone' => 'nullable|regex:/(0)[0-9]{9}/',
            'logo' => 'required|file|image|max:20000',
            'cover' => 'nullable|file|image|max:20000',
            'point_trigger' => 'nullable|string',
            'latitude' => 'nullable|numeric',
            'longitude' => 'nullable|numeric'
        ]);

        if ($request->hasFile('logo')) {
            $logo_path = cloudinary()->upload($request->file('logo')->getRealPath())->getSecurePath();
            $validate['logo'] = $logo_path;
        }

        if ($request->hasFile('cover')) {
            $cover_path = cloudinary()->upload($request->file('cover')->getRealPath())->getSecurePath();
            $validate['cover'] = $cover_path;
        }

        $shop->fill($validate);
        $shop->save();

        return Response("Cập nhật thành công", 200);
    }

    /**
     * 16 - Xóa. Xóa shop cho manager
     *
     * Thực hiện xóa shop của user hiện tại
     *
     */
    public function destroy(Request $request)
    {
        $shop = $request->user->shop;
        if (!$shop) {
            return Response("Không tìm thấy shop!", 404);
        }

        $shop->delete();
        return Response("Xóa shop thành công!", 200);
    }


    /**
     * 8. Lấy thông tin cửa hàng theo id
     *
     * Dữ liệu trả về gồm các ưu đãi, dịch vụ, mô tả cửa hàng (dùng ở trang chi tiết cửa hàng).
     * Nếu user là user thì trả về số điểm hiện tại của user ở shop này
     */
    public function getShopById(Request $request, string $id){
        $shop = Shop::find($id);
        if($shop){
            $shop->load('coupons', 'services');
            if ($request->user->role == RoleEnum::User){
                $shop_point = $request->user->points()->where('shop_id', $shop->_id)->first();
                if ($shop_point){
                    $shop->your_points = $shop_point->points;
                }
            }
            return $shop;
        }
        return Response('Không tìm thấy shop này!', 404);
    }

    /**
     * 17. Quét QR người dùng tích điểm hoặc nhận ưu đãi
     *
     * Admin thực hiện quét mã tích điểm cho người dùng nếu có mã quà tặng thì sẽ cho đổi quà, nếu không thì tích điểm
     *
     */
    public function scanQR(Request $request)
    {
        $validated = $request->validate([
            'user_id' => 'required|string',
            'reward_id' => 'nullable|string',
            'service_id' => 'nullable|string|exists:services,_id',
        ]);

        $customer = User::find($validated['user_id']);

        if ($customer){
            return Response('Khách hàng không tồn tại!', 404);
        }

        if (!$request->user->shop){
            return Response('Bạn không có shop!', 404);
        }

        if ($request->has('service_id') && $request->has('reward_id')) {
            return Response('Vui lòng chỉ chọn 1 trong tích điểm hoặc nhận ưu đãi', 404);
        }

        // Admin quét mã tích điểm
        if ($request->has('service_id')) {
            // Kiểm tra xem service có trong shop hay không
            $service = $request->user->shop->services()->find($validated['service_id']);
            if (!$service){
                return Response('Dịch vụ không tồn tại!', 404);
            }

            // Tiến hành cộng điểm, kiểm tra user đã có điểm ở shop này chưa
            $point = $customer->points()->where('shop_id', $request->user->shop->_id)->first();
            DB::beginTransaction();
            try {
                if ($point) {
                    $point->points += $service->points_reward;
                    $current_point = $point->points;
                    $point->save();
                }

                else {
                    $customer->points()->create([
                        'shop_id' => $request->user->shop->_id,
                        'points' => $service->points_reward
                    ]);
                    $current_point = $service->points_reward;
                }

                Transaction::create([
                    'user_id' => $customer->_id,
                    'service_id' => $service->_id,
                    'type' => 'plus',
                    'point' => $service->points_reward,
                    'current_point' => $current_point,
                    'reason' => 'Cộng điểm cho dịch vụ '.$service->name
                ]);
                DB::commit();
            } catch (\Exception $e) {
                DB::rollBack();
                return Response('Tích điểm thất bại!', 404);
            }

            return Response('Tích điểm thành công!', 200);
        }

        // Admin quét mã xem quà tặng cho user
        if ($request->has('reward_id')) {
            $coupon = $customer->coupons()->find($validated['reward_id']);
            if (!$coupon){
                return Response('Không tìm thấy mã ưu đãi!', 404);
            }

            if ($coupon->shop_id != $request->user->shop->_id){
                return Response('Mã ưu đãi không thuộc shop này!', 404);
            }

            if ($coupon->redeemed_at){
                return Response('Mã ưu đãi được sử dụng ngày '.$coupon->redeemed_at, 404);
            }

            if ($coupon->expired_at < now()){
                return Response('Mã ưu đãi đã hết hạn!', 404);
            }

            $coupon->redeemed_at = now();
            $coupon->save();

            return Response([
                'message' => 'Đổi quà thành công!',
                'coupon' => $coupon
            ], 200);
        }

        return Response('Dữ liệu không hợp lệ', 404);
    }

}
