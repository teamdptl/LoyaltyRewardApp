<?php

use Illuminate\Support\Facades\Route;

Route::get('/', function () {
    return view('welcome');
});

Route::get('/post/{title}', [\App\Http\Controllers\PostController::class, 'show']);
