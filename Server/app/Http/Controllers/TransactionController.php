<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use App\Models\Coupon;
use App\Models\Service;
use App\Models\Transaction;
use App\Models\User;
use Google\Rpc\Context\AttributeContext\Response;
use Illuminate\Http\Request;

class TransactionController extends Controller
{
    public function scanQRCode(Request $request)
    {
        $validated = $request->validate([
            'user_id' => 'required|string',
            'reward_id' => 'required|string',
        ]);

        $user = User::find($request->input('user_id', ''));
        if (!$user) {
            return Response('User không tồn tại!', 404);
        }
    }

    public function index(){
        return Transaction::all();
    }

    public function show($id){
        return Transaction::find($id);
    }

    public function findTransactionsByUserId($userId){
        $listTrans = Transaction::where('user_id', $userId)->get();
        if(sizeof($listTrans) > 0){
            return $listTrans;
        }else{
            return Response('Chưa có giao dịch nào!');
        }
        return Response('Lỗi!', 404);
    }

    public function store(Request $request){
        $validated = $request->validate([
            'user_id' =>'required|string',
            'service_id' =>'nullable|string',
            'coupon_id' =>'nullable|string',
            'type' =>'required|in:plus,minus',
            'resson' => 'string',
        ]);

        $transaction = null;
        $coupon = null;
        $service = Service::find($request->input('service_id', ''));
        //Nếu không tìm thấy dịch vụ thì tiến hành tìm coupon
        if(!$service){
            $coupon = Coupon::find($request->input('coupon_id', ''));
            //Nếu không tìm thấy cả coupon ở đây thì báo lỗi
            if(!$coupon){
                return Response('Mã dịch vụ và mã khuyến mãi không hợp lệ! Không thể tạo giao dịch', 404);
            }else{
                //Tìm thấy coupon
                //Kiểm tra nếu loại giao dịch là đổi coupon thì thực hiện
                if($request->input('type', '') == 'minus'){
                    //Tiến hành lưu giao dịch và cập nhật điểm của người dùng
                    $transaction = new Transaction($validated);
                    $transaction->coupon()->associate($coupon);
                }else{
                    //Nếu loại giao dịch không hợp lệ báo lỗi cho dev
                    return Response('Dev ơi loại giao dịch không đúng rồi:\'((', 404);
                }
            }
        }else{
            //Tìm thấy dịch vụ
            //Kiểm tra nếu loại giao dịch là sử dụng dịch vụ thì thực hiện
            if($request->input('type', '') == 'plus'){
                //Tiến hành lưu giao dịch và cập nhật điểm của người dùng
                $transaction = new Transaction($validated);
                $transaction->service()->associate($service);
            }else{
                //Nếu loại giao dịch không hợp lệ báo lỗi cho dev
                return Response('Dev ơi loại giao dịch không đúng rồi:\'((', 404);
            }
        }

        $user = User::find($request->input('user_id',''));
        if(!$user){
            return Response('User không tồn tại!', 404);
        }
        $userController = new UserController();
        $updatePointResult = $userController->updatePoint($user, $service, $coupon);
        if($updatePointResult[0] == 'success'){
            $transaction->user()->associate($user);
            $transaction->save();
            return $transaction;
        }
        return Response("Lỗi ".$updatePointResult[1]. " không hợp lệ!", 404);
    }
}
