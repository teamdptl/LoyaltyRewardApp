<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use App\Http\Services\AuthService;
use App\Models\User;
use Illuminate\Http\Request;

class UserController extends Controller
{
    public function store(Request $request){
        /**
         * Lấy các thuôc tính của user từ request
         */
        $name = $request->input('name', '');
        $role = $request->input('role', '');

        /**
         * Nếu không nhận đc uid sau khi sign up ở login thì yêu cầu để nhận uid
         * ở đoạn này
         */

        // $auth = new AuthService();
        // $uid = $auth->validateIdToken($request->header('Authorization', ''));

        /**
         * Nếu nhận đc uid sau khi tạo thì dùng nó để bắt đầu tạo user
        */
        $uid = $request->input('uid');

        $user = new User([
            '_id' => $uid,
            'name' => $name,
            'role' => $role,
            'fcmtoken' => '',// chưa bít cái này lưu j có j nhắn tuấn sửa
        ]);
        return $user->save();

    }
}
