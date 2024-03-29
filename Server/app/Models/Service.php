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

    
    public function shop(){
        return $this->belongsToMany(Shop::class);
    }

    public function transactions(){
        return $this->hasMany(Transaction::class);
    }
}
