<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

return new class extends Migration
{
    /**
     * Run the migrations.
     */
    public function up(): void
    {
        Schema::create('users_coupons', function (Blueprint $collection) {
            $collection->foreignId('users_id')->constrained('users')->onDelete('cascade');
            $collection->foreignId('coupons_id')->constrained('coupons')->onDelete('cascade');
            $collection->timestamp('redeemed_at')->nullable();
            $collection->timestamp('expired_at')->nullable();
            $collection->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('users_coupons');
    }
};
