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
        'created_at'  => 'datetime:d-m-Y H:m',
        'updated_at'  => 'datetime:d-m-Y H:m',
        'expired_at'  => 'datetime:d-m-Y H:m',
        'redeemed_at'  => 'datetime:d-m-Y H:m'
    ];

    protected $hidden = ['updated_at'];

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
