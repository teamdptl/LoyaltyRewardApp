<?php

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use Illuminate\Support\Facades\Redis;

//Route::get('/test-redis', function(){
//    $isExistName = true;
//    $name = Redis::get('name');
//    if (!$name){
//        $isExistName = false;
//        $name = "Duy Huynh";
//        Redis::set('name', $name);
//    }
//    return ['name' => $name, 'isExistName' => $isExistName];
//});


//Route::get('/user', function (Request $request) {
//    return $request->user;
//})->middleware('auth-firebase');


//Route::get('/shop/all', [App\Http\Controllers\ShopController::class, 'index']);
//Route::get('/shop/{id}', [App\Http\Controllers\ShopController::class, 'show']);
//Route::post('/shop/create', [App\Http\Controllers\ShopController::class, 'store']);

//Route::get('/service/all', [App\Http\Controllers\ServiceController::class, 'index']);
//Route::get('service/{id}', [App\Http\Controllers\ServiceController::class, 'show']);
//Route::post('service/create', [App\Http\Controllers\ServiceController::class, 'store']);

//Route::get('/shop/{shopId}/coupon', [App\Http\Controllers\CouponController::class, 'findCouponsByShopId']);
//Route::get('/shop/{shopId}/coupon/create', [App\Http\Controllers\CouponController::class, 'store']);

//Route::get('/user/all', [App\Http\Controllers\UserController::class, 'index']);
//Route::get('user/show', [App\Http\Controllers\UserController::class, 'show']);
//Route::post('/user/create', [App\Http\Controllers\UserController::class, 'store']);
//Route::get('/user/points/{shopId}', [App\Http\Controllers\UserController::class, 'getPointByShopId']);

//Route::post('/transaction/create', [App\Http\Controllers\TransactionController::class, 'store']);


// ---------------- API đã hoàn thành -----------------

// API đăng kí tài khoản
//Route::post('/register', [App\Http\Controllers\UserController::class, 'store']);

// 0. Trả về idToken dùng để xác thực user thông qua API header authorization
//Route::get('/token', [App\Http\Controllers\TestController::class, 'getToken']);

Route::post('/create-user', [App\Http\Controllers\AuthController::class, 'createUsers']);

Route::post('/send-otp', [App\Http\Controllers\AuthController::class, 'sendOTP']);

Route::middleware('auth-firebase')->group(function () {

    // 1. Lấy thông tin của người dùng (bao gồm thông tin cơ bản, mã qr, vai trò, shop) (Dùng chung vs quản lý)
    Route::get('/user', [App\Http\Controllers\UserController::class, 'show']);

    // 8. API trả về thông tin gồm thông tin chi tiết, danh sách dịch vụ, danh sách khuyến mãi
    Route::get('/shop/{id}', [App\Http\Controllers\ShopController::class, 'getShopById']);

    // 9. Lấy thông tin chi tiết của ưu đãi
    Route::get('/coupon/{id}', [App\Http\Controllers\CouponController::class, 'show']);

    // 17. Upload file (Dùng chung)
    Route::post('/upload', [App\Http\Controllers\FileController::class, 'upload']);

    // 15. Cập nhật FCM Token
    Route::post('/fcm', [App\Http\Controllers\UserController::class, 'updateFCMToken']);

    Route::middleware('auth-firebase-user')->group(function () {
        // API dùng riêng cho user

        // 2. Lấy danh sách cửa hàng đề xuất (Các cửa hàng gần nhất và mới mở)
        Route::get('/user/recommended', [App\Http\Controllers\UserController::class, 'getRecommendedShop']);

        // 3. Lấy danh sách cửa hàng đã ghé thăm của người dùng
        Route::get('/user/visited', [App\Http\Controllers\UserController::class, 'getVisitedShop']);

        // 4. Lấy danh sách điểm các cửa hàng của người dùng
        Route::get('/user/points', [App\Http\Controllers\UserController::class, 'getListPoint']);

        // 5. Lấy danh sách coupon có thể đổi được của người dùng với các cửa hàng
        Route::get('/user/coupons/available', [App\Http\Controllers\UserController::class, 'getAvailableCoupons']);

        // 6. Lấy lịch sử giao dịch (transaction)
        Route::get('/user/transaction', [App\Http\Controllers\UserController::class, 'getTransaction']);

        // 10. Đổi điểm thành ưu đãi
        Route::post('/user/exchange', [App\Http\Controllers\UserController::class, 'exchangeCoupon']);

        // 11. Lấy danh sách ưu đãi của người dùng
        Route::get('/user/coupons', [App\Http\Controllers\UserController::class, 'getCoupons']);

        // 30. Lấy chi tiết coupon
        Route::get('/user/coupon/{id}', [App\Http\Controllers\UserController::class, 'getCouponById']);
    });

    Route::middleware('auth-firebase-shop-owner')->group(function () {
        // API dùng để quản lý shop
        Route::get('/shop', [App\Http\Controllers\ShopController::class, 'show']);
        Route::post('/shop', [App\Http\Controllers\ShopController::class, 'store']);
        Route::post('/shop', [App\Http\Controllers\ShopController::class, 'update']);

        // Delete shop sẽ gây ra rất nhiều lỗi :((
        Route::delete('/shop', [App\Http\Controllers\ShopController::class, 'destroy']);

        // Coupon: Ưu đãi
        Route::post('/shop/coupon', [App\Http\Controllers\CouponController::class, 'store']);
        Route::post('/shop/coupon/{id}', [App\Http\Controllers\CouponController::class, 'update']);
        Route::delete('/shop/coupon/{id}', [App\Http\Controllers\CouponController::class, 'destroy']);

        // Service: Dịch vụ
        Route::post('/shop/service', [App\Http\Controllers\ServiceController::class, 'store']);
        Route::post('/shop/service/{id}', [App\Http\Controllers\ServiceController::class, 'update']);
        Route::delete('/shop/service/{id}', [App\Http\Controllers\ServiceController::class, 'destroy']);

        // 17. Quét mã qr của người dùng để tích điểm hoặc nhận ưu đãi
        Route::post('/shop/scan', [App\Http\Controllers\ShopController::class, 'scanQR']);

        // Lấy thông tin của cửa hàng và thống kê trong ngày của cửa hàng (lượt quét, điểm đã cấp, quà đã đổi, danh sách ghé thăm trong ngày đã quét)
        Route::get('/shop-daily', [App\Http\Controllers\ShopController::class, 'getShopDailyStatistic']);

        // Lấy thông tin chi tiết user để tích điểm
        Route::get('/user/{id}', [App\Http\Controllers\UserController::class, 'findUser']);
    });

    Route::middleware('auth-firebase-admin')->group(function () {
        // API dùng riêng cho admin
    });
});
