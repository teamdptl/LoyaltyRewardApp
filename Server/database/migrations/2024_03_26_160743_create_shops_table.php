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
        Schema::create('shops', function (Blueprint $collection) {
            $collection->string('name');
            $collection->string('address');
            $collection->string('phone');
            $collection->string('logo')->nullable();
            $collection->string('cover')->nullable();
            $collection->string('point_trigger');
            $collection->json('extra_info')->nullable();
            $collection->foreignId('user_id')->constrained('users')->onDelete('cascade');
            $collection->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     */
    public function down(): void
    {
        Schema::dropIfExists('shops');
    }
};
