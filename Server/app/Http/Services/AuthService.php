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
            $this->auth = (new Factory)->withServiceAccount([
                'type' => 'service_account',
                'project_id' => 'loyaltyreward-95033',
                'private_key_id' => '6a56df21285e92b8a5f198c48ee280f1a02b9e87',
                'private_key' => '-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCiVPNaQ/d2kYET\nFvO6nTPK0Adz3eerIfC3Qng5aqilEG8UyqOOnF3MLFrIu3Qt4Rtc3+017PqXkE4E\n9qHKEkEAtP4U+IfhtEOZKZYKKtpPQ5iGwjYGYhpxBBqxhwS6ve594EjqVr7ELJ9C\nXcMZfF7DLZxPMmCPd0DyGuIfsXx+8X758IEkcFBm8IqXe8eyiiGAfI/8vy7rtsP0\nEDY5OM2bZa+o4xqe/spgGw5HDUeIF+UWqAz/w4zhDyJFhOwMR7NROZye71JG9lJy\nc5WnzMterU1VdzUX9FlmPaAphScn1boq3W12SrXl3TOUt6QPz5VhRdJf+dyUgM+c\nsD0pxp4rAgMBAAECggEACSs5L5EFNN+VEHShwZmdfPLGaPymAtwrkij2ohpBz55K\nV+dLg65Ap1vzU2vbF7hOPlmw4UndrEU3Uupx9aLq+ao/a68kpafz5X5xfhOGOdHn\nQef4UItRU67X/yJRhGaz8MzZRtCC0pEepwtQDhbLQhpHvjq/NNCXN4AzOM0NQMeL\nd9lkWdGp6RBSzJOCHSynWzjWlzDJTTyEnJxqRrP4kHHhnZqUIA/tpyQhNnu/qZEu\nlbwlMCUPEIxck/aRLH3dczafN0UTRiSd1tA4JcynrGaPomrlmFl4q+m2iZxeFuHI\nwhhjAWfW3Pr3mwvv1+Kms6cFQYrcpinTh3DJfc+twQKBgQDN5erS41KAGUpPlxia\nJThq2UAZ653kH39KL4z4ApdDp2Ad5rTiNmT0kpPkwOP2Ye7rVugS8/kpaRQwkzsO\nqezUIlQsMEL89mgVq4hle37Kf44ZiT6VrJYBNSkym3mOuZMBefI0vmLHT8EXmjpr\nZQBAjTLlblIqe9RO3oZbk4WsIQKBgQDJ1STnOdqWdb6tAQL6PFQBTnJk9benYC5n\nRTyp3HpamOjpJAaWplUQ0BoTU/p2v8s7ncQP+N7Mek1ZRdDOQfCx+LHFgRJ3El33\nYbyIQYzEJZ+yDwmGdZltU9b2Hp1+V0NGy8+wj4FgBJzu6NbhfomgJMEkF9hepNjs\ncxUL2OMgywKBgEvFjvNpANVveS9tFXq0s9xucu6XDRYEmV19vk0O9wfIstCONIcD\nKYfv4UlPyxTC2Z/17ZX7/vfEWc++fv9YfXVb5MoWQLDDJW7EH3/pQLHY2zx+i2+d\ngd6T58NeABAMLxNeA0pxvfxHV3IOKe2Xz83xqIdSYB6OT4Vu0tckpQ7BAoGBAJGD\nvJGA4pnr1kdBHXZwnFg8yLkFdx2TeKE4B96neQdsiy/bq2xeC6KNht88gq2u5cXb\nnwms8mUWIEIm1b5/SBvoI7Mzta5nbZv3uUe7itO4FIT51nI8wxtRgP/9XqtCGt0m\nAF1ZeXtSmBqfgJ0e7/anySBOmM7TpxoCdsAN5OM1AoGAAqPJGPPevzi2/21JsNP2\nFw1S2jkJ1bztgUMxcjz2U/sUmCWnuAmrxYqmmsNDLEigfMyF4Dxhg3E0YyiC5Cvn\n5oGyRPjXXOLYdDGga7tDTijkz7z+Jli7/T6u5zQgSWSWGSs1NS9R4s0WrnPpqmY+\nZSyOWJYzoFqhbWSkHJogRog=\n-----END PRIVATE KEY-----\n',
                'client_email' => 'ffirebase-adminsdk-e67la@loyaltyreward-95033.iam.gserviceaccount.com',
                'client_id' => '109332615241030969620',
                'auth_uri' => 'https://accounts.google.com/o/oauth2/auth',
                'token_uri' => 'https://oauth2.googleapis.com/token',
                'auth_provider_x509_cert_url' => 'https://www.googleapis.com/oauth2/v1/certs',
                'client_x509_cert_url' => 'hhttps://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-e67la%40loyaltyreward-95033.iam.gserviceaccount.com',
                'universe_domain' => 'googleapis.com',
            ])->createAuth();
//            $this->auth = app('firebase.auth');
        } catch (Exception $e){
            print("Thiếu định nghĩa file loyalty_reward.json ở trong .env rồi! Liên hệ Duy đi");
            print($e);
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
        //    return 'The token is invalid: '.$e->getMessage();
        } catch (AuthException|FirebaseException $e) {
        //    return 'An error occurred: '.$e->getMessage();
        }
        return null;
    }
}
