<?php
namespace App\Http\Controllers;

use App\Enums\RoleEnum;
use App\Http\Services\AuthService;
use App\Models\Coupon;
use App\Models\Reminder;
use App\Models\Shop;
use App\Models\Transaction;
use App\Models\User;
use Carbon\Carbon;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;
use Kreait\Firebase\Auth\UserQuery;
use Symfony\Component\HttpFoundation\Response;

/**
 * @tags Cửa hàng (Shop)
 */
class ShopController extends Controller
{
    // TODO: API thứ 2 - Đề xuất cho user các shop gần nhất
    public function index()
    {
        return Shop::all();
    }

    /**
     * 16 - Xem. Xem shop cho manager
     *
     * Thực hiện xem shop
     *
     */
    public function show(Request $request)
    {
        if (!$request->user->shop) {
            return Response("User chưa có shop!", 400);
        }
        return $request->user->shop->load('services', 'coupons');
    }

    /**
     * 16 - Tạo. Tạo shop cho manager
     *
     * Thực hiện tạo shop với các thuộc tính như tên, mô tả, địa chỉ, điện thoại, logo, cover, cách tính điểm (point trigger), vĩ độ, kinh độ
     *
     */
    public function store(Request $request)
    {
        if ($request->user->shop) {
            return Response("User đã có shop!", 400);
        }

        $validate = $request->validate([
            'name' => 'required|string',
            'description' => 'nullable|string',
            'address' => 'required|string',
            'phone' => 'nullable|regex:/(0)[0-9]{9}/',
            'logo' => 'required|string',
            'cover' => 'nullable|string',
            'point_trigger' => 'nullable|string',
            'latitude' => 'nullable|numeric',
            'longitude' => 'nullable|numeric'
        ]);

        //Lưu file logo và cover vào storage
//        if ($request->hasFile('logo')) {
//            $logo_path = cloudinary()->upload($request->file('logo')->getRealPath())->getSecurePath();
//            $validate['logo'] = $logo_path;
//        }
//
//        if ($request->hasFile('cover')) {
//            $cover_path = cloudinary()->upload($request->file('cover')->getRealPath())->getSecurePath();
//            $validate['cover'] = $cover_path;
//        }

        $shop = new Shop($validate);

        if ($request->has('latitude') && $request->has('longitude'))
            $shop->location = [
                'type' => 'Point',
                'coordinates' => [(double)$validate['longitude'], (double)$validate['latitude']]
            ];

        //Có thể dùng 1 trong 2 cách bên dưới để lưu shop có relate to user
        $request->user->shop()->save($shop);

        return Response(
            ['message' => "Tạo shop thành công!"], 200);
    }

    /**
     * 16 - Sửa. Sửa shop cho manager
     *
     * Thực hiện sửa shop với các thuộc tính như tên, mô tả, địa chỉ, điện thoại, logo, cover, cách tính điểm (point trigger), vĩ độ, kinh độ
     *
     */
    public function update(Request $request)
    {
        $shop = $request->user->shop;
        if (!$shop) {
            return Response("Không tìm thấy shop!", 404);
        }

        $validate = $request->validate([
            'name' => 'required|string',
            'description' => 'nullable|string',
            'address' => 'required|string',
            'phone' => 'nullable|regex:/(0)[0-9]{9}/',
            'logo' => 'required|string',
            'cover' => 'nullable|string',
            'point_trigger' => 'nullable|string',
            'latitude' => 'nullable|numeric',
            'longitude' => 'nullable|numeric'
        ]);

//        if ($request->hasFile('logo')) {
//            $logo_path = cloudinary()->upload($request->file('logo')->getRealPath())->getSecurePath();
//            $validate['logo'] = $logo_path;
//        }
//
//        if ($request->hasFile('cover')) {
//            $cover_path = cloudinary()->upload($request->file('cover')->getRealPath())->getSecurePath();
//            $validate['cover'] = $cover_path;
//        }

        if ($request->has('latitude') && $request->has('longitude'))
            $shop->location = [
                'type' => 'Point',
                'coordinates' => [(double)$validate['longitude'], (double)$validate['latitude']]
            ];

        $shop->fill($validate);
        $shop->save();

        return Response(['message' => "Cập nhật thành công"], 200);
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
        return Response(['message' => "Xóa shop thành công!"], 200);
    }


    /**
     * 8. Lấy thông tin cửa hàng theo id
     *
     * Dữ liệu trả về gồm các ưu đãi, dịch vụ, mô tả cửa hàng (dùng ở trang chi tiết cửa hàng).
     * Nếu user là user thì trả về số điểm hiện tại của user ở shop này
     */
    public function getShopById(Request $request, string $id)
    {
        $shop = Shop::find($id);
        if ($shop) {
            $shop->load('coupons', 'services');
            if ($request->user->role == RoleEnum::User) {
                $shop_point = $request->user->points()->where('shop_id', $shop->_id)->first();
                if ($shop_point) {
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
     * Admin thực hiện quét mã tích điểm cho người dùng nếu có mã quà tặng thì sẽ cho đổi quà, nếu không thì tích điểm \n
     *
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

        if (!$customer) {
            return Response('Khách hàng không tồn tại!', 404);
        }

        if (!$request->user->shop) {
            return Response('Bạn không có shop!', 404);
        }

        if ($request->has('service_id') && $request->has('reward_id')) {
            return Response('Vui lòng chỉ chọn 1 trong tích điểm hoặc nhận ưu đãi', 404);
        }

        // Admin quét mã tích điểm
        if ($request->has('service_id')) {
            // Kiểm tra xem service có trong shop hay không
            $service = $request->user->shop->services()->find($validated['service_id']);
            if (!$service) {
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
                } else {
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
                    'reason' => 'Cộng điểm cho dịch vụ ' . $service->name,
                    'shop_id' => $request->user->shop->_id,
                ]);

                if ($service->should_notification) {
                    $customer->reminders()->create([
                        'title' => 'Nhắc nhở ' . $service->name . ' định kì!',
                        'description' => 'Hãy đến cửa hàng ' . $request->user->shop->name . ' để sử dụng dịch vụ ' . $service->name . ' định kì nhé!',
                        'image' => $request->user->shop->logo,
                        'time' => now()->addDays($service->period),
                        'shop_id' => $request->user->shop->_id
                    ]);
                }

                DB::commit();
            } catch (\Exception $e) {
                DB::rollBack();
                return Response('Tích điểm thất bại!', 404);
            }

            return Response(['message' => 'Tích điểm thành công!'], 200);
        }

        // Admin quét mã xem quà tặng cho user
        if ($request->has('reward_id')) {
            $coupon = $customer->coupons()->find($validated['reward_id']);
            if (!$coupon) {
                return Response('Không tìm thấy mã ưu đãi!', 404);
            }

            if ($coupon->shop_id != $request->user->shop->_id) {
                return Response('Mã ưu đãi không thuộc shop này!', 404);
            }

            if ($coupon->redeemed_at) {
                return Response('Mã ưu đãi được sử dụng ngày ' . $coupon->redeemed_at, 404);
            }

            if ($coupon->expired_at < now()) {
                return Response('Mã ưu đãi đã hết hạn!', 404);
            }

            DB::beginTransaction();
            try {
                $coupon->redeemed_at = Carbon::now()->toIso8601String();
                $coupon->save();

                Transaction::create([
                    'user_id' => $customer->_id,
                    'coupon_id' => $coupon->_id,
                    'type' => 'receive',
                    'point' => 0,
                    'current_point' => $customer->points()->where('shop_id', $request->user->shop->_id)->first()->points,
                    'reason' => 'Đã đổi quà ' . $coupon->name . ' thành công!',
                    'shop_id' => $request->user->shop->_id,
                ]);
                DB::commit();
            } catch (\Exception $e) {
                DB::rollBack();
                return Response('Đổi quà thất bại!', 404);
            }

            return Response([
                'message' => 'Đổi quà thành công \'' . $coupon->name . "'!",
                'coupon' => $coupon
            ], 200);
        }

        return Response('Dữ liệu không hợp lệ', 404);
    }

    /**
     * 20. Lấy thông tin của cửa hàng và thống kê trong ngày của cửa hàng (lượt quét, điểm đã cấp, quà đã đổi, danh sách ghé thăm trong ngày đã quét)
     *
     * Trả về thông tin thống kê như lượt tích điểm, lượt đổi quà, điểm đã cấp, danh sách người dùng đã đến cửa hàng trong ngày
     *
     */
    public function getShopDailyStatistic(Request $request)
    {
        $today = Carbon::today();
        $tomorrow = Carbon::tomorrow();
        $transaction = $request->user->shop->transactions()->with('user')
            ->whereDate('created_at', '>=', $today)
            ->whereDate('created_at', '<', $tomorrow)->get();
        $luotTichDiem = $transaction->where('type', 'plus')->count();
        $luotDoiQua = $transaction->where('type', 'minus')->count();
        $luotDoiQuaTaiCuaHang = $transaction->where('type', 'receive')->count();
        $diemDaCap = $transaction->where('type', 'plus')->sum('point');
        $transaction = $this->mapTransactionWithFirebaseUser($transaction);

        return Response([
            'luotTichDiem' => $luotTichDiem,
            'luotDoiQua' => $luotDoiQua,
            'luotDoiQuaTaiCuaHang' => $luotDoiQuaTaiCuaHang,
            'diemDaCap' => $diemDaCap,
            'visited' => $transaction
        ], 200);
    }

    function mapTransactionWithFirebaseUser($transaction){
        $userQuery = UserQuery::all()
            ->sortedBy(UserQuery::FIELD_USER_EMAIL)
            ->inDescendingOrder()
            ->withOffset(1)
            ->withLimit(499);

        $auth = app('firebase.auth');
        $users = $auth->queryUsers($userQuery);

        return $transaction->map(function($item) use ($users){
            $auth_id = $item->user->auth_id;
            $user = $users[$auth_id] ?? null;
            if ($user){
                $item->name = $user->displayName;
                $item->photo = $user->photoUrl ?? "https://cdn.icon-icons.com/icons2/1378/PNG/512/avatardefault_92824.png";
            }
            unset($item->user);
            return $item;
        });
    }
}
