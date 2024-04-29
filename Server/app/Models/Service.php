<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use MongoDB\Laravel\Eloquent\Model;

class Service extends Model
{
    use HasFactory;
    protected $connection = 'mongodb';
    protected $fillable = [
        'name',
        'description',
        'should_notification',
        'period',
        'points_reward'
    ];

    protected $casts = [
        'should_notification' => 'boolean',
        'period' => 'integer',
        'points_reward' => 'integer',
        'created_at'  => 'datetime:d-m-Y H:m',
        'updated_at'  => 'datetime:d-m-Y H:m'
    ];

    protected $hidden = ['created_at'];


    public function shop(){
        return $this->belongsTo(Shop::class);
    }

    public function coupons(){
        return $this->hasMany(Coupon::class);
    }

    public function transactions(){
        return $this->hasMany(Transaction::class);
    }

    public function users(){
        return $this->belongsToMany(User::class)->withPivot('using_at');
    }
}
