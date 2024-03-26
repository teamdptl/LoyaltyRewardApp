<?php
namespace App\Http\Controllers;

use App\Models\Shop;
use Illuminate\Http\Request;

class ShopController extends Controller{
    public function index(){
        return Shop::all();
    }

    public function store(Request $request){
        $shop = new Shop;
        $shop->name = 'Hớt tóc Duy';
        $shop->address = 'Cho xin cái địa chỉ';
        $shop->logo = 'path-to-logo';

        //Cần lấy userID thông qua firebase và dùng nó để lưu shop của người dùng
        $shop->save();
        return 'Hello';
    }
}