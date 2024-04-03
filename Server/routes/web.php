<?php

use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('welcome');
});

Route::get('/info', function () {
    phpinfo();
});

Route::get('/shop', [App\Http\Controllers\ShopController::class, 'index']);
Route::post('/shop/store', [App\Http\Controllers\ShopController::class, 'store']);