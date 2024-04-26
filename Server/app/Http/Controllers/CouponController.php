<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use App\Models\Coupon;
use App\Models\Shop;
use Illuminate\Auth\Events\Validated;
use Illuminate\Http\Request;
use Symfony\Component\HttpFoundation\Response;

class CouponController extends Controller
{
    public function index(){
        return Coupon::all();
    }

//    public function findCouponsByShopId($shopId){
//        // printf($shopId);
//        $listCoupon = Coupon::where('shop_id', $shopId)->get();
//        if(sizeof($listCoupon) > 0){
//            return $listCoupon;
//        }else{
//            return Response('Chưa có khuyến mãi!');
//        }
//        return Response('Lỗi!', 404);
//    }

//    public function findCouponsByUserId($userId){
//        // printf($userId);
//        $listCoupon = Coupon::where('user_id', $userId)->get();
//        if(sizeof($listCoupon) > 0){
//            return $listCoupon;
//        }else{
//            return Response('Chưa có khuyến mãi!');
//        }
//        return Response('Lỗi!', 404);
//    }

    public function show($id){
        $coupon = Coupon::find($id);
        if($coupon){
            return $coupon;
        }else{
            return Response('Không tìm thấy khuyến mãi này!', 404);
        }
        return Response('Lỗi!', 404);
    }


    /**
     * 18 - Tạo. Tạo ưu đãi cho shop
     *
     * Tạo ưu đãi với các thông tin như tên, mô tả, số điểm cần có, icon, trạng thái kích hoạt
     *
     */
    public function store(Request $request){
        $validated = $request->validate([
            'name' => 'required|string',
            'description' => 'required|string',
            'require_point' => 'required|numeric|min:0',
            'icon' => 'required|file|image|max:20000',
            'is_active' => 'nullable|boolean',
        ]);

        $icon_path = cloudinary()->upload($request->file('icon')->getRealPath())->getSecurePath();
        $validated['icon'] = $icon_path;

        $shop = $request->user->shop;
        if(!$shop){
            return Response('Không tìm thấy cửa hàng!', 404);
        }

        $coupon = new Coupon($validated);
        $shop->coupons()->save($coupon);
        return Response('Tạo ưu đãi thành công!', 200);
    }

    /**
     * 18 - Sửa. Sửa ưu đãi cho shop
     *
     * Sửa ưu đãi với các thông tin như tên, mô tả, số điểm cần có, icon, trạng thái kích hoạt
     *
     */
    public function update(Request $request, string $id){
        $validated = $request->validate([
            'name' => 'required|string',
            'description' => 'required|string',
            'require_point' => 'required|integer|min:0',
            'icon' => 'required|file|image|max:20000',
            'is_active' => 'nullable|boolean',
        ]);

        $icon_path = cloudinary()->upload($request->file('icon')->getRealPath())->getSecurePath();
        $validated['icon'] = $icon_path;

        $shop = $request->user->shop;
        if(!$shop){
            return Response('Không tìm thấy cửa hàng!', 404);
        }

        $coupon = Coupon::find($id);

        if(!$coupon || $coupon->shop_id != $shop->id){
            return Response('Không tìm thấy ưu đãi!', 404);
        }

        $coupon->update($validated);
        return Response('Sửa ưu đãi thành công!', 200);
    }

    /**
     * 18 - Xóa. Xóa ưu đãi cho shop
     *
     * Xóa ưu đãi với id
     *
     */
    public function destroy(Request $request, string $id)
    {
        $validated = $request->validate([
            'id' => 'required|string',
        ]);

        $shop = $request->user->shop;
        if (!$shop) {
            return Response('Không tìm thấy cửa hàng!', 404);
        }

        $coupon = Coupon::find($id);
        if (!$coupon || $coupon->shop_id != $shop->id) {
            return Response('Không tìm thấy ưu đãi!', 404);
        }

        $coupon->delete();
        return Response('Xóa ưu đãi thành công!', 200);
    }

}
