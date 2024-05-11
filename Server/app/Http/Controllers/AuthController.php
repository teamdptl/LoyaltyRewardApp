<?php

namespace App\Http\Controllers;

use App\Models\OtpCode;
use Dedoc\Scramble\Support\Generator\Response;
use HTTP_Request2;
use HTTP_Request2_Exception;
use Illuminate\Http\Request;
use Twilio\Rest\Client;

class AuthController extends Controller
{
    /**
     * 14_Tạo user mới
     *
     * Tạo user mới với thông tin cơ bản như tên, số điện thoại, mật khẩu, role và mã OTP đã gửi
     *
     */
    public function createUsers(Request $request)
    {
        $validate = $request->validate([
            'name' => 'required|string',
            'phone' => 'required|regex:/^(0)[0-9]{9}$/',
            'password' => 'required|string',
            'role' => 'required|string|in:manager,user',
            'otp' => 'required|string|size:6',
        ]);

        // Check otp code
        $num = '84'.substr($validate['phone'], 1);
        $otp = OtpCode::where('phone', $num)
            ->where('otp_code', $validate['otp'])
            ->where('expired_at', '>', now()->toIso8601String())
            ->first();

        if (!$otp){
            return Response('Mã OTP không hợp lệ', 401);
        }

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
            return response()->json('Số điện thoại đã tồn tại', 401);
        } catch (\Kreait\Firebase\Exception\Auth\InvalidPassword $e) {
            return response()->json('Mật khẩu không hợp lệ', 401);
        } catch (\Kreait\Firebase\Exception\Auth\OperationNotAllowed $e) {
            return response()->json('Tạo user không được phép', 401);
        } catch (\Kreait\Firebase\Exception\Auth\WeakPassword $e) {
            return response()->json('Mật khẩu quá yếu',401);
        } catch (\Exception $e){
            return response()->json('Tạo user thất bại', 401);
        }

        return Response(['message' => 'Tạo user thành công', 'data' => $user]);
    }


    /**
     * 14_Gửi SMS OTP cho user mới tạo tài khoản
     *
     * Nhập số điện thoại và server sẽ trả về OTP. Mỗi otp sẽ tồn tại 5 phút
     *
     */
    public function sendOTP(Request $request){
        // Validate phone
        $validate = $request->validate([
            'phone' => 'required|regex:/^(0)[0-9]{9}$/',
        ]);

        $phone = '84'.substr($validate['phone'], 1);

        // Check old otp model exist with expired_at > now
        $oldOtp = OtpCode::where('phone', $phone)
            ->where('expired_at', '>', now()->toIso8601String())
            ->first();

        if ($oldOtp){
            return Response('Vui lòng chờ 5 phút để gửi lại', 400);
        }

        // Mở comment để generate code
        $otp = "";
        for ($i = 0; $i < 6; $i++){
            $otp .= rand(0, 9);
        }

        $content =  "Mã OTP của bạn là: $otp. Mã OTP sẽ hết hạn sau 5 phút.";

        // Save otp code to database
        OtpCode::create([
            'phone' => $phone,
            'otp_code' => $otp,
            'expired_at' => now()->addMinutes(5)->toIso8601String(),
        ]);


        // Mở comment để gửi OTP qua InfoBip
        $request = new HTTP_Request2();
        $request->setUrl('https://rgzz5y.api.infobip.com/sms/2/text/advanced');
        $request->setMethod(HTTP_Request2::METHOD_POST);
        $request->setConfig(array(
            'follow_redirects' => TRUE
        ));
        $request->setHeader(array(
            'Authorization' => 'App '.env("SMS_SECRET_KEY"),
            'Content-Type' => 'application/json',
            'Accept' => 'application/json'
        ));

        $request->setBody('{"messages":[{"destinations":[{"to":"'.$phone.'"}],"from":"ServiceSMS","text":"'.$content.'"}]}');
        try {
            $response = $request->send();
            return Response(['message' => 'Gửi mã OTP thành công'], 200);
        }
        catch(HTTP_Request2_Exception $e) {
            return Response( 'Gửi mã OTP thất bại');
        }
    }
}
