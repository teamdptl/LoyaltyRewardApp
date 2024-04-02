<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use App\Http\Services\AuthService;
use App\Models\Coupon;
use App\Models\Service;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Http\Response;

class UserController extends Controller
{

    public function index(){
        return User::all();
    }

    public function show(Request $request){
        $token = $request->header('Authorization','');
        $uid = (new AuthService())->validateIdToken($token);
        return User::find($uid);
    }

    public function store(Request $request){
        /**
         * Validate các thuôc tính của user từ request
         */
        $validate = $request->validate([
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
        $user_point = $user->user_point;
        if(sizeof($user_point) > 0){
            //Kiểm tra service và coupon
            if(!$service){
                foreach($user_point as $shop){
                    if($shop->coupons->contains($coupon)){
                        $point = $shop->pivot->points; 
                        if($point >= $coupon->require_point){
                            echo "in khuyến mãi";
                            $user->user_point()->updateExistingPivot($shop->_id, ['points' => $coupon->require_point]);
                            return ["success", "khuyến mãi"];
                        }else{
                            return ["fail", "khuyến mãi"];
                        }
                    }
                }
                return ["fail", "khuyến mãi"];
            }else{
                foreach($user_point as $shop){
                    if($shop->services->contains($service)){
                        echo "in dịch vụ";
                        $user->user_point()->updateExistingPivot($shop->_id, ['points', $service->points_reward]); 
                        return ["success", "services"];
                    }
                }

                //Nếu khách hàng chưa có điểm ở shop đó thì sẽ add new record
                $shop = $service->shop;
                echo "in tạo mới";
                $user->user_point()->attach($shop->_id, ['points' => $service->points_reward]);
                return ["success", "services"];
            }
        }else{
            //Nếu khách hàng chưa có điểm nào thì sẽ add new record
            $shop = $service->shop;
            echo "in tạo mới". $shop->_id;
            $user->user_point()->attach($shop->_id, ['points' => $service->points_reward], true);
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
        foreach($user_point as $shop){
            // if($shop->_id == $shopId){
            //     echo $shop->pivot;
            // }
            echo $shop->pivot->points;
        }
    }
}
