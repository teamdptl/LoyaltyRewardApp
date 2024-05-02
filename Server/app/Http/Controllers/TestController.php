<?php

namespace App\Http\Controllers;

use App\Http\Services\AuthService;
use Illuminate\Http\Request;
use Kreait\Firebase\Auth;

/**
 * @tags Token
 *
 */
class TestController extends Controller
{

    /**
     * 0. Trả về idToken dùng để xác thực user thông qua API header Authorization
     *
     * Đưa vào role user hay manager(chủ shop) để lấy idToken.
     * Sau đó để vào header Authorization để xác thực user
     *
     * Có thể tùy chọn pass_login hoặc phone_login để đăng nhập bằng mật khẩu hoặc số điện thoại
     */
    public function test(Request $request){
        $validate = $request->validate([
            'role' => 'required|string|in:default_user,default_manager,pass_login,phone_login',
            'username' => 'nullable|string',
            'password' => 'nullable|string',
        ]);

        $auth = app('firebase.auth');
        try {
            if ($validate['role'] == 'default_user'){
                $signInResult = $auth->signInWithEmailAndPassword('user@app.vn', '1234567');
            } else if($validate['role'] == 'default_manager'){
                $signInResult = $auth->signInWithEmailAndPassword('manager@app.vn', '1234567');
            } else if($validate['role'] == 'pass_login'){
                $signInResult = $auth->signInWithEmailAndPassword($validate['username'], $validate['password']);
            } else if($validate['role'] == 'phone_login'){
                // SDT: 0123456789@app.vn
                $signInResult = $auth->signInWithEmailAndPassword($validate['username']."@app.vn", $validate['password']);
            }
            return $signInResult->data();
        } catch (\Kreait\Firebase\Auth\SignIn\FailedToSignIn $e) {
            return response()->json(['message' => 'Đăng nhập thất bại'], 401);
        }
    }
}
