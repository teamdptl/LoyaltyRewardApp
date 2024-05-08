<?php

namespace Database\Factories;

use Illuminate\Database\Eloquent\Factories\Factory;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Str;

/**
 * @extends \Illuminate\Database\Eloquent\Factories\Factory<\App\Models\User>
 */
class UserFactory extends Factory
{
    /**
     * The current password being used by the factory.
     */
    protected static ?string $password;

    /**
     * Define the model's default state.
     *
     * @return array<string, mixed>
     */
    public function definition(): array
    {
        // Create user in firebase
        $auth = app('firebase.auth');
        // create a string with 9 number with faker
        $phone = '+84'.$this->faker->randomNumber(9, true);
        $email = $phone.'@app.vn';
        $password = "1234567";
        $name = $this->faker->name();
        $user = $auth->createUser([
                'email' => $email,
                'phoneNumber' => $phone,
                'password' =>$password,
                'displayName' => $name,
        ]);

        return [
            'auth_id' => $user->uid,
            'role' => 'manager',
            'fcm_token' => "",
        ];
    }

    /**
     * Indicate that the model's email address should be unverified.
     */
    public function unverified(): static
    {
        return $this->state(fn (array $attributes) => [
            'email_verified_at' => null,
        ]);
    }
}
