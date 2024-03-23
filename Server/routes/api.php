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

Route::post('/show/{title}', [\App\Http\Controllers\PostController::class, 'show']);
Route::post('/create', [\App\Http\Controllers\PostController::class, 'store']);

Route::get('/user', function (Request $request) {
    return $request->user();
})->middleware('auth:sanctum');
