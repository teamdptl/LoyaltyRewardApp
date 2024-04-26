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
        'is_active'
    ];

    public function users(){
        return $this->belongsToMany(User::class)->withPivot(['expired_at', 'redeemed_at']);
    }

    public function shop(){
        return $this->belongsTo(Shop::class);
    }

    public function service(){
        return $this->belongsTo(Service::class);
    }
}
