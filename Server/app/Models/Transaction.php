<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use MongoDB\Laravel\Eloquent\Model;

class Transaction extends Model
{
    use HasFactory;
    protected $connection = 'mongodb';

    protected $fillable = [
        'type',
        'point',
        'reason',
        'shop_id',
        'user_id',
        'service_id',
        'coupon_id',
        'current_point',
    ];

    protected $casts = [
        'point' => 'integer',
        'current_point' => 'integer',
        'created_at'  => 'datetime:d-m-Y H:m',
        'updated_at'  => 'datetime:d-m-Y H:m'
    ];

    protected $hidden = ['updated_at'];

    public function user(){
        return $this->belongsTo(User::class);
    }

    public function service(){
        return $this->belongsTo(Service::class);
    }

    public function coupon(){
        return $this->belongsTo(Coupon::class);
    }

    public function shop(){
        return $this->belongsTo(Shop::class, 'shop_id');
    }
}
