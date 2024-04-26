<?php

namespace App\Models;

// use Illuminate\Contracts\Auth\MustVerifyEmail;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Notifications\Notifiable;
use MongoDB\Laravel\Eloquent\Model;

class User extends Model
{
    use HasFactory;
    // use Notifiable;
    protected $connection = 'mongodb';
    /**
     * The attributes that are mass assignable.
     *
     * @var array<int, string>
     */
    protected $fillable = [
        'name',
        'role',
        'fcm_token'
    ];


    public function shop(){
        return $this->hasOne('App\Models\Shop', 'user_id');
    }

    // public function user_point(){
    //     return $this->belongsToMany(Shop::class, 'users_shops', 'users_id', 'shops_id');
    //                 // ->withTimestamps()
    //                 // ->withPivot(['points'])
    //                 // ->as('user_points');
    // }

    public function point(){
        return $this->hasMany(Point::class);
    }

    public function transactions(){
        return $this->hasMany('App\Models\Transaction');
    }

    public function coupon(){
        return $this->belongsToMany('App\Models\Coupon')->withPivot(['expired_at', 'redeemed_at']);
    }

    public function services(){
        return $this->hasMany(Service::class)->withPivot('using_at');
    }
}
