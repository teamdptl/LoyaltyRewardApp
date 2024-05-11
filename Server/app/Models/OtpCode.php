<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use MongoDB\Laravel\Eloquent\Model;

class OtpCode extends Model
{
    use HasFactory;
    protected $connection = 'mongodb';

    protected $fillable = [
        'phone',
        'otp_code',
        'expired_at',
    ];
}
