<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use MongoDB\Laravel\Eloquent\Model;

class Reminder extends Model
{
    use HasFactory;

    protected $connection = 'mongodb';

    protected $fillable = [
        'title',
        'description',
        'time',
        'image',
        'user_id',
        'shop_id',
    ];

    protected $casts = [
        'time' => 'datetime',
    ];

    public function user()
    {
        return $this->belongsTo(User::class, 'user_id');
    }

    public function shop()
    {
        return $this->belongsTo(Shop::class, 'shop_id');
    }
}
