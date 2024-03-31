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


Route::get('/shop/all', [App\Http\Controllers\ShopController::class, 'index']);
Route::get('/shop/{id}', [App\Http\Controllers\ShopController::class, 'show']);
Route::post('/shop/create', [App\Http\Controllers\ShopController::class, 'store']);

Route::get('/service/all', [App\Http\Controllers\ServiceController::class, 'index']);
Route::get('service/{id}', [App\Http\Controllers\ServiceController::class, 'show']);
Route::post('service/create', [App\Http\Controllers\ServiceController::class, 'store']);

Route::get('/user/all', [App\Http\Controllers\UserController::class, 'index']);
Route::get('user/show', [App\Http\Controllers\UserController::class, 'show']);
Route::post('/user/create', [App\Http\Controllers\UserController::class, 'store']);