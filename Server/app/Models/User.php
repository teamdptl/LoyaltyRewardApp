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

    /**
     * The attributes that should be hidden for serialization.
     *
     * @var array<int, string>
     */
    protected $hidden = [
        'password',
        'remember_token',
    ];

    /**
     * Get the attributes that should be cast.
     *
     * @return array<string, string>
     */
    protected function casts(): array
    {
        return [
            'email_verified_at' => 'datetime',
            'password' => 'hashed',
        ];
    }


    public function shop(){
        return $this->hasOne('App\Models\Shop');
    }

    public function user_point(){
        return $this->belongsToMany('App\Models\Shop')->withPivot('points');
    }

    public function transactions(){
        return $this->hasMany('App\Models\Transaction');
    }

    public function coupon(){
        return $this->belongsToMany('App\Models\Coupon')->withPivot('expired_at');
    }
}
