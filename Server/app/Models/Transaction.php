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
        'resson'
    ];

    public function user(){
        return $this->belongsTo(User::class);
    }

    public function service(){
        return $this->belongsTo(Service::class);
    }

    public function coupon(){
        return $this->belongsTo(Coupon::class);
    }
}
