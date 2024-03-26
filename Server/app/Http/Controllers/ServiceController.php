<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use App\Models\Service;
use App\Models\User;
use Illuminate\Http\Request;

class ServiceController extends Controller
{
    public function index(){
        return Service::all();
    }

    public function store(Request $request){
        $service = new Service;
        $service->name = 'Thay nhớt';
        $service->description = 'Nhớt castrol mới siêu mạnh mẽ lun';
        $service->points_reward = 10;

        //Tương tự bên Shop cần userID để xác định shop của user
        $shop = User::find($request->input('user_id'))->shop()->get();

        //Lưu service của shop
        $shop->service()->create($service);
    }
}
