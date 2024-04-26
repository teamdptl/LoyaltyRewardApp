<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use App\Http\Services\AuthService;
use App\Models\Coupon;
use App\Models\Point;
use App\Models\Service;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Http\Response;

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
        $request->user->load('shop');

        if ($request->user->role == 'user'){
            $request->user->qr = $request->user->id;
        }

        return $request->user;
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
        $user_point = $user->point;

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
