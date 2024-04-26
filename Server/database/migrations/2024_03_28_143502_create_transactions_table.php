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
        Schema::create('transactions', function (Blueprint $collection) {
            $collection->string('type');
            $collection->integer('point');
            $collection->text('reason')->nullable();
            $collection->foreignId('user_id')->constrained('users')->onDelete('cascade');
            $collection->foreignId('service_id')->nullable()->constrained('services')->onDelete('cascade');
            $collection->foreignId('coupon_id')->nullable()->constrained('coupons')->onDelete('cascade');
            $collection->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('transactions');
    }
};
