<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class AuthController extends Controller
{
    public function createUsers(Request $request)
    {
        $validate = $request->validate([
            'name' => 'required|string',
            'phone' => 'required|regex:/^(0)[0-9]{9}$/',
            'password' => 'required|string',
        ]);
        $auth = app('firebase.auth');
        $phone = '+84'.substr($validate['phone'], 1);
        try {
            $user = $auth->createUser([
                'email' => $phone.'@app.vn',
                'phoneNumber' => '+84'.substr($validate['phone'], 1),
                'password' => $validate['password'],
                'displayName' => $validate['name'],
            ]);
        } catch (\Kreait\Firebase\Exception\Auth\EmailExists|\Kreait\Firebase\Exception\Auth\PhoneNumberExists $e) {
            return response()->json(['message' => 'Số điện thoại đã tồn tại'], 401);
        } catch (\Kreait\Firebase\Exception\Auth\InvalidPassword $e) {
            return response()->json(['message' => 'Mật khẩu không hợp lệ'], 401);
        } catch (\Kreait\Firebase\Exception\Auth\OperationNotAllowed $e) {
            return response()->json(['message' => 'Tạo user không được phép'], 401);
        } catch (\Kreait\Firebase\Exception\Auth\WeakPassword $e) {
            return response()->json(['message' => 'Mật khẩu quá yếu'], 401);
        } catch (\Exception $e){
            return response()->json(['message' => 'Tạo user thất bại'], 401);
        }
        return Response(['message' => 'Tạo user thành công', 'data' => $user]);
    }
}
