<?php

namespace App\Providers;

use App\Http\Services\DeleteExpiredNotificationTokens;
use http\Url;
use Illuminate\Support\Facades\Event;
use Illuminate\Support\ServiceProvider;

class AppServiceProvider extends ServiceProvider
{
    /**
     * Register any application services.
     */
    public function register(): void
    {
        Event::listen(
            \Illuminate\Notifications\Events\NotificationFailed::class,
            DeleteExpiredNotificationTokens::class,
        );
    }

    /**
     * Bootstrap any application services.
     */
    public function boot(): void
    {
        //
    }
}
