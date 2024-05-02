<?php

namespace App\Http\Services;

use Illuminate\Notifications\Events\NotificationFailed;
use Illuminate\Support\Arr;

class DeleteExpiredNotificationTokens{
    public function handle(NotificationFailed $event): void
    {
        $report = Arr::get($event->data, 'report');

        $target = $report->target();

        $event->notifiable->notificationTokens()
            ->where('push_token', $target->value())
            ->delete();
    }
}
