<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Transaction>
 */
class TransactionFactory extends Factory
{
    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */

    public function definition(): array
    {
        return [
            'type' => $this->faker->randomElement(['plus', 'minus', 'receive']),
            'point' => $this->faker->numberBetween(1, 100),
            'reason' => $this->faker->text,
            'current_point' => $this->faker->numberBetween(1, 100),
        ];
    }
}
