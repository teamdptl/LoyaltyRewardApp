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
        Schema::create('coupons', function (Blueprint $collection) {
            $collection->string('name');
            $collection->text('description')->nullable();
            $collection->integer('require_point', false, true)->default(0);
            $collection->string('icon');
            $collection->boolean('is_active')->default(true);
            $collection->foreignId('shop_id')->constrained('shops')->onDelete('cascade');
            $collection->timestamp('expired_at')->nullable();
            $collection->timestamp('redeemed_at')->nullable();
            $collection->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('coupons');
    }
};
