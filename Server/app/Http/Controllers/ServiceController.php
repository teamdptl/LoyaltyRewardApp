<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use App\Http\Services\AuthService;
use App\Models\Service;
use App\Models\User;
use Illuminate\Http\Request;
use Kreait\Firebase\Contract\Auth;

class ServiceController extends Controller
{

    public function index(){
        return Service::all();
    }

    public function show($id){
        return Service::find($id);
    }


    public function store(Request $request){

        $validated = $request->validate(
            [
                'name' =>'required|string|max:255',
                'description' =>'required|string|max:255',
                'should_notification' =>'required|boolean',
                'period' => 'required|string',
                'points_reward' => 'required|digits_between:1,2',
            ]
        );
        // printf($tokenID);
        $auth = new AuthService();
        $uid = $auth->validateIdToken($request->header('Authorization', ''));
        
        if(!$uid){
            return Response("Token không hợp lệ!", 404);
        }

        $user = User::find($uid);
        if(!$user){
            return Response("User không tồn tại!", 404);
        }

        $shop = $user->shop()->get()->first();
        if(!$shop){
            return Response("User không đủ quyền truy cập trang này!", 404);
        }

        $service = new Service($validated);
        $service->shop()->associate($shop);
        return $service->save();
    }
}
