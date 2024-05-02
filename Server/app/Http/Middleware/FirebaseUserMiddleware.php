<?php

namespace App\Http\Middleware;

use App\Http\Services\AuthService;
use App\Models\User;
use Closure;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Cache;
use Illuminate\Support\Facades\Redis;
use Symfony\Component\HttpFoundation\Response;

class FirebaseUserMiddleware
{
    /**
     * Handle an incoming request.
     *
     * @param  \Closure(\Illuminate\Http\Request): (\Symfony\Component\HttpFoundation\Response)  $next
     */
    public function handle(Request $request, Closure $next): Response
    {
        // TODO: Bỏ phần này, phần này chỉ hỗ trợ test
//        $request->user = User::find('662fb7178a9bdf8c710d42f3');
//        $request->user = User::find('662fc03229cf8e79fe0b3882'); // 6630590cf6659302fe0fe302
//        return $next($request);

        $header = $request->header('Authorization');
        if (!$header)
            return response()->json(['message' => 'Bạn không có quyền truy cập trang này'], 401);
        $idToken = explode(' ', $header)[1];
        $auth = new AuthService();
        $uid = $auth->validateIdToken($idToken);
        if (!$uid)
            return response()->json(['message' => 'Bạn không có quyền truy cập trang này'], 401);
        // Get data from cache
        $cache_user = Cache::get($uid);

        if ($cache_user){
            $user = $cache_user;
        } else {
            $user = User::where('auth_id', $uid)->first();
            if (!$user)
                return response()->json(['message' => 'Bạn không có quyền truy cập trang này'], 401);
            Cache::put($uid, $user, 60*10);
        }

        $request->user = $user;
        return $next($request);
    }
}
