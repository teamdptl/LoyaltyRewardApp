<?php

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use Illuminate\Support\Facades\Redis;

Route::get('/test-redis', function(){
    $isExistName = true;
    $name = Redis::get('name');
    if (!$name){
        $isExistName = false;
        $name = "Duy Huynh";
        Redis::set('name', $name);
    }
    return ['name' => $name, 'isExistName' => $isExistName];
});


Route::get('/user', function (Request $request) {
    return $request->user;
})->middleware('auth-firebase');

//Route::middleware('auth-firebase')->group(function () {
//    Route::middleware('auth-firebase-user')->group(function () {
//        Route::get('/user', function (Request $request) {
//            return $request->user;
//        });
//    });
//});


Route::get('/shop', [App\Http\Controllers\ShopController::class, 'index']);
Route::post('/shop/store', [App\Http\Controllers\ShopController::class, 'store']);