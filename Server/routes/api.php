<?php

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;

Route::get('/test', function(){
    return User::where('id', '=', 1)->first();
});

Route::post('/show/{title}', [\App\Http\Controllers\PostController::class, 'show']);
Route::post('/create', [\App\Http\Controllers\PostController::class, 'store']);

Route::get('/user', function (Request $request) {
    return $request->user();
})->middleware('auth:sanctum');
