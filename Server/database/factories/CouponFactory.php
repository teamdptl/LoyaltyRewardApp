<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Coupon>
 */
class CouponFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        return [
            'name' => $this->faker->name,
            'description' => $this->faker->text,
            'require_point' => $this->faker->numberBetween(1, 100),
            'icon' => $this->faker->imageUrl(),
            'is_active' => true,
            'expired_after' => $this->faker->numberBetween(1, 30),
            'expired_at' => $this->faker->dateTimeBetween('now', '+1 month'),
            'redeemed_at' => $this->faker->dateTimeBetween('now', '+1 month'),
        ];
    }
}
