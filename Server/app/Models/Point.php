<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use MongoDB\Laravel\Eloquent\Model;

class Point extends Model
{
    use HasFactory;

    protected $connection ='mongodb';

    protected $fillable = [
        'points',
        'shop_id',
    ];

    protected $casts = [
        'points' => 'integer',
        'created_at'  => 'datetime:d-m-Y H:m',
        'updated_at'  => 'datetime:d-m-Y H:m'
    ];

    public function user(){
        return $this->belongsTo(User::class);
    }

    public function shop(){
        return $this->belongsTo(Shop::class, 'shop_id', '_id');
    }
}
