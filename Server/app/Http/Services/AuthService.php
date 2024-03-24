<?php

namespace App\Http\Services;

use Exception;
use Kreait\Firebase\Contract\Auth;
use Kreait\Firebase\Exception\Auth\FailedToVerifyToken;
use Kreait\Firebase\Exception\AuthException;
use Kreait\Firebase\Exception\FirebaseException;
use Kreait\Firebase\Factory;

class AuthService
{
    private Auth $auth;

    public function __construct()
    {
        try{
            $factory = (new Factory)->withServiceAccount(env('FIREBASE_CREDENTIALS', ''));
            $this->auth = $factory->createAuth();
        } catch (Exception $e){
            print("Thiếu định nghĩa file loyalty_reward.json ở trong .env rồi! Liên hệ Duy đi");
            exit(0);
        }
    }

    // Kiểm tra xem idToken có hợp lệ không ở trong firebase auth
    public function validateIdToken(string $idTokenString): ?string
    {
        try {
            $verifiedIdToken = $this->auth->verifyIdToken($idTokenString);
            return $verifiedIdToken->claims()->get('sub');
            // $user = $this->auth->getUser($uid);
            // return $user;
        } catch (FailedToVerifyToken $e) {
//            echo 'The token is invalid: '.$e->getMessage();
        } catch (AuthException|FirebaseException $e) {
//            echo 'An error occurred: '.$e->getMessage();
        }
        return null;
    }
}
