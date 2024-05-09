<?php

namespace Database\Factories;

use App\Enums\PointMethod;
use Illuminate\Database\Eloquent\Factories\Factory;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\Shop>
 */
class ShopFactory extends Factory
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
            'phone' => $this->faker->phoneNumber,
            'address' => $this->faker->address,
            'logo' => $this->faker->imageUrl(),
            'cover' => $this->faker->imageUrl(),
            'point_trigger' => PointMethod::ByVisit,
            'location' => [
                'type' => 'Point',
                'coordinates' => [
                    $this->faker->longitude,
                    $this->faker->latitude
                ]
            ]
        ];
    }
}

