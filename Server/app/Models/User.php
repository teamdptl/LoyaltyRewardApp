<?php

namespace App\Models;

// use Illuminate\Contracts\Auth\MustVerifyEmail;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Notifications\Notifiable;
use MongoDB\Laravel\Eloquent\Model;

class User extends Model
{
    use HasFactory;
    use Notifiable;

    protected $connection = 'mongodb';
    /**
     * The attributes that are mass assignable.
     *
     * @var array<int, string>
     */
    protected $fillable = [
        'auth_id',
        'role',
        'fcm_token'
    ];

    protected $casts = [
        'created_at'  => 'datetime:d-m-Y H:m',
        'updated_at'  => 'datetime:d-m-Y H:m'
    ];

    protected $hidden = [
        'password',
        'email_verified_at',
        'fcm_token',
        'remember_token',
        'updated_at',
    ];

    /**
     * Specifies the user's FCM token
     *
     * @return string|array
     */
    public function routeNotificationForFcm()
    {
        return $this->fcm_token;
    }

    public function shop(){
        return $this->hasOne('App\Models\Shop', 'user_id');
    }

    // public function user_point(){
    //     return $this->belongsToMany(Shop::class, 'users_shops', 'users_id', 'shops_id');
    //                 // ->withTimestamps()
    //                 // ->withPivot(['points'])
    //                 // ->as('user_points');
    // }

    public function points(){
        return $this->embedsMany(Point::class);
    }

    public function transactions(){
        return $this->hasMany(Transaction::class, 'user_id');
    }

    public function coupons(){
        return $this->embedsMany(Coupon::class);
    }

    public function services(){
        return $this->hasMany(Service::class)->withPivot('using_at');
    }

    public function reminders(){
        return $this->hasMany(Reminder::class);
    }
}
