<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use MongoDB\Laravel\Eloquent\Model;

class Coupon extends Model
{
    use HasFactory;
    protected $connection = 'mongodb';
    protected $fillable = [
        'name',
        'description',
        'require_point',
        'icon',
        'is_active',
        'expired_after',
        'expired_at',
        'redeemed_at',
    ];

    protected $casts = [
        'require_point' => 'integer',
        'is_active' => 'boolean',
        'expired_after' => 'integer',
    ];

    public function user(){
        return $this->belongsToMany(User::class);
    }

    public function shop(){
        return $this->belongsTo(Shop::class);
    }

    public function service(){
        return $this->belongsTo(Service::class);
    }
}
