<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use App\Models\Coupon;
use App\Models\Shop;
use Illuminate\Auth\Events\Validated;
use Illuminate\Http\Request;

class CouponController extends Controller
{
    public function index(){
        return Coupon::all();
    }

    public function findCouponsByShopId($shopId){
        // printf($shopId);
        $listCoupon = Coupon::where('shop_id', $shopId)->get();
        if(sizeof($listCoupon) > 0){
            return $listCoupon;
        }else{
            return Response('Chưa có khuyến mãi!');
        }
        return Response('Lỗi!', 404);
    }

    public function findCouponsByUserId($userId){
        // printf($userId);
        $listCoupon = Coupon::where('user_id', $userId)->get();
        if(sizeof($listCoupon) > 0){
            return $listCoupon;
        }else{
            return Response('Chưa có khuyến mãi!');
        }
        return Response('Lỗi!', 404);
    }

    public function show($id){
        $coupon = Coupon::find($id);
        if($coupon){
            return $coupon;
        }else{
            return Response('Không tìm thấy khuyến mãi này!', 404);
        }
        return Response('Lỗi!', 404);
    }

    public function store(Request $request, $shopId){
        $validated = $request->validate([
            'name' => 'required',
            'description' => 'required',
            'require_point' => 'numeric',
            'icon' => 'required|string',
            'is_active' => 'nullable|boolean',
        ]);

        $shop = Shop::find($shopId);
        if(!$shop){
            return Response('Không tìm thấy cửa hàng!', 404);
        }

        $coupon = new Coupon($validated);
        $coupon->shop()->associate($shop);
        return $coupon->save();
    }
}
