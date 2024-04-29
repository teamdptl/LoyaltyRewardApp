<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use MongoDB\Laravel\Eloquent\Model;

class Shop extends Model
{
    use HasFactory;
    protected $connection ='mongodb';
    protected $fillable = ['name', 'address', 'logo', 'cover', 'point_trigger'];

    protected $casts = [
        'created_at'  => 'datetime:d-m-Y H:m',
        'updated_at'  => 'datetime:d-m-Y H:m'
    ];

    protected $hidden = [
        'updated_at',
        'user_id',
    ];
    public function user(){
        return $this->belongsTo(User::class, 'user_id');
    }

    public function services(){
        return $this->hasMany(Service::class);
    }

    public function coupons(){
        return $this->hasMany(Coupon::class);
    }

    // public function user_point(){
    //     return $this->belongsToMany(User::class);
    //                 // ->withPivot(['points'])
    //                 // ->as('user_points');
    // }

    public function point(){
        return $this->hasMany(Point::class);
    }


}
