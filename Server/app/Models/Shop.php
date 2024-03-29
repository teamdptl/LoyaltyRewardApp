<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use MongoDB\Laravel\Eloquent\Model;

class Shop extends Model
{
    use HasFactory;
    protected $connection ='mongodb';
    protected $collection = 'Shop';
    protected $fillable = ['name', 'address', 'logo', 'point_trigger'];

    public function user(){
        return $this->belongsTo(User::class);
    }

    public function services(){
        return $this->hasMany(Service::class);
    }

    public function coupons(){
        return $this->hasMany(Coupon::class);
    }


}
