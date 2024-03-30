<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use App\Http\Services\AuthService;
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
}
