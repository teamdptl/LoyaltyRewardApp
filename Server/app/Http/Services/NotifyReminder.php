<?php

namespace App\Http\Services;

use App\Models\Coupon;
use App\Models\Reminder;
use App\Notifications\CouponReminder;
use App\Notifications\ServiceReminder;
use Carbon\Carbon;

class NotifyReminder
{
    public function __invoke()
    {
        $today = Carbon::today();
        $twoDaysLater = Carbon::today()->addDays(60);

        // Nhắc nhở dịch vụ định kì
        $reminders = Reminder::whereDate('time', '>=', $today)
            ->whereDate('time', '<', $twoDaysLater)
            ->get();
        $reminders->each(function ($reminder) {
            $reminder->user->notify(new ServiceReminder($reminder));
        });

        // Nhắn nhở coupon hết hạn
        $coupons = Coupon::whereDate('expired_at', '>=', $today)
            ->whereDate('expired_at', '<', $twoDaysLater)
            ->whereNull('redeemed_at')
            ->get();
        $coupons->each(function ($coupon) {
            $coupon->user->notify(new CouponReminder($coupon));
        });
    }
}
