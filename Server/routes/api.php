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


//Route::get('/shop/all', [App\Http\Controllers\ShopController::class, 'index']);
//Route::get('/shop/{id}', [App\Http\Controllers\ShopController::class, 'show']);
//Route::post('/shop/create', [App\Http\Controllers\ShopController::class, 'store']);

Route::get('/service/all', [App\Http\Controllers\ServiceController::class, 'index']);
Route::get('service/{id}', [App\Http\Controllers\ServiceController::class, 'show']);
//Route::post('service/create', [App\Http\Controllers\ServiceController::class, 'store']);

Route::get('/shop/{shopId}/coupon', [App\Http\Controllers\CouponController::class, 'findCouponsByShopId']);
Route::get('/shop/{shopId}/coupon/create', [App\Http\Controllers\CouponController::class, 'store']);

Route::get('/user/all', [App\Http\Controllers\UserController::class, 'index']);
//Route::get('user/show', [App\Http\Controllers\UserController::class, 'show']);
Route::post('/user/create', [App\Http\Controllers\UserController::class, 'store']);
Route::get('/user/points/{shopId}', [App\Http\Controllers\UserController::class, 'getPointByShopId']);

Route::post('/transaction/create', [App\Http\Controllers\TransactionController::class, 'store']);


// ---------------- API đã hoàn thành -----------------
Route::middleware('auth-firebase')->group(function () {

    // 1. Lấy thông tin của người dùng (bao gồm thông tin cơ bản, mã qr, vai trò, shop) (Dùng chung vs quản lý)
    Route::get('user/show', [App\Http\Controllers\UserController::class, 'show']);

    // 8. API trả về thông tin gồm thông tin chi tiết, danh sách dịch vụ, danh sách khuyến mãi
    Route::get('/shop/{id}', [App\Http\Controllers\ShopController::class, 'getShopById']);

    Route::middleware('auth-firebase-user')->group(function () {
        // API dùng riêng cho user
    });

    Route::middleware('auth-firebase-shop-owner')->group(function () {
        // API dùng để quản lý shop
        Route::get('/shop', [App\Http\Controllers\ShopController::class, 'show']);
        Route::post('/shop', [App\Http\Controllers\ShopController::class, 'store']);
        Route::put('/shop', [App\Http\Controllers\ShopController::class, 'update']);
        Route::delete('/shop', [App\Http\Controllers\ShopController::class, 'destroy']);

        // Coupon: Ưu đãi
        Route::post('/shop/coupon', [App\Http\Controllers\CouponController::class, 'store']);
        Route::put('/shop/coupon/{id}', [App\Http\Controllers\CouponController::class, 'update']);
        Route::delete('/shop/coupon/{id}', [App\Http\Controllers\CouponController::class, 'destroy']);

        // Service: Dịch vụ
        Route::post('/shop/service', [App\Http\Controllers\ServiceController::class, 'store']);
        Route::put('/shop/service/{id}', [App\Http\Controllers\ServiceController::class, 'update']);
        Route::delete('/shop/service/{id}', [App\Http\Controllers\ServiceController::class, 'destroy']);


//        Route::post('/transaction', [App\Http\Controllers\TransactionController::class, 'store']);
    });

    Route::middleware('auth-firebase-admin')->group(function () {
        // API dùng riêng cho admin
    });
});
