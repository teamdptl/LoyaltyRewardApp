<?php

namespace App\Notifications;

use Illuminate\Bus\Queueable;
use Illuminate\Contracts\Queue\ShouldQueue;
use Illuminate\Notifications\Messages\MailMessage;
use Illuminate\Notifications\Notification;
use NotificationChannels\Fcm\FcmChannel;
use NotificationChannels\Fcm\FcmMessage;
use NotificationChannels\Fcm\Resources\Notification as FcmNotification;

class CouponReminder extends Notification
{
    use Queueable;

    /**
     * Create a new notification instance.
     */
    public function __construct($coupon)
    {
        $this->coupon = $coupon;
    }

    public function via(object $notifiable): array
    {
        return [FcmChannel::class];
    }

    public function toFcm($notifiable): FcmMessage
    {
        return (new FcmMessage(notification: new FcmNotification(
            title: "Sắp hết hạn ".$this->coupon->name,
            body: "Hãy đến cửa hàng ".$this->coupon->shop->name." để sử dụng coupon trước khi hết hạn",
            image: $this->coupon->icon,
        )))
            ->data([
                'title' => "Sắp hết hạn ".$this->coupon->name,
                'body' => "Hãy đến cửa hàng ".$this->coupon->shop->name." để sử dụng coupon trước khi hết hạn",
                'image' => $this->coupon->icon,
                'shop' => $this->coupon->shop,
            ])
            ->custom([
                'android' => [
                    'notification' => [
                        'color' => '#0A0A0A',
                    ],
                    'fcm_options' => [
                        'analytics_label' => 'analytics',
                    ],
                ],
                'apns' => [
                    'fcm_options' => [
                        'analytics_label' => 'analytics',
                    ],
                ],
            ]);
    }
}
