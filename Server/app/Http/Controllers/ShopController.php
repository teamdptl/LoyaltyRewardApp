<?php
namespace App\Http\Controllers;

use App\Http\Services\AuthService;
use App\Models\Shop;
use App\Models\User;
use Illuminate\Http\Request;

class ShopController extends Controller{
    public function index(){
        return Shop::all();
    }

    public function show($id){
        return Shop::find($id);
    }

    public function store(Request $request){
        $validate = $request->validate([
            'name' => 'required|string',
            'address' => 'required|string',
            'logo' => 'required|string',
            'point_trigger' => 'nullable|string',
        ]);

        $tokenID = $request->header('Authorization', '');
        $auth = new AuthService();
        $uid = $auth->validateIdToken($tokenID);
    
        if(!$uid){
            return Response("Token không hợp lệ!", 404);
        }

        $user = User::find($uid);
        if(!$user){
            return Response("User không tồn tại!", 404);
        }

        if($user->role != 'manager'){
            return Response("Bạn không có quyền truy cập trang này", 401);
        }

        $shop = new Shop($validate);

        //Có thể dùng 1 trong 2 cách bên dưới để lưu shop có relate to user
        $shop->user()->associate($user);
        // $user->shop()->save($shop);
        
        
        return $shop->save();
    }
}