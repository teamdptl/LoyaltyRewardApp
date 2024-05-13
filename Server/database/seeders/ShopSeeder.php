<?php

namespace Database\Seeders;

use App\Enums\PointMethod;
use App\Models\Shop;
use App\Models\User;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;

class ShopSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        $userManager1 = User::factory()->create();
        $userManager2 = User::factory()->create();
        $userManager3 = User::factory()->create();
        $userManager4 = User::factory()->create();

        $tiemHotToc = Shop::factory()->create([
            'name' => 'Barber Shop Mr.Duy',
            'description' => 'Tiệm cắt tóc nam nữ, nhuộm tóc, uốn tóc, làm móng',
            'phone' => '0123456789',
            'address' => '123 Đường 123, Quận 1, TP.HCM',
            'logo' => 'https://res.cloudinary.com/hmvv5mjf0/image/upload/v1715094485/static/fbnsguj8vztt7a6gf1tn.jpg',
            'cover' => 'https://res.cloudinary.com/hmvv5mjf0/image/upload/v1715094633/static/ej47jg4ikmlx6aylsdhy.jpg',
            'point_trigger' => PointMethod::ByVisit,
            'location' => [
                'type' => 'Point',
                'coordinates' => [
                    106.6858067,
                    10.749321
                ]
            ]
        ]);

        $tiemHotToc->user_id = $userManager1->_id;
        $tiemHotToc->save();

        $tiemHotToc->services()->create([
            'name' => 'Cắt tóc nam, nữ',
            'description' => 'Cắt tóc nam theo kiểu',
            'should_notification' => true,
            'period' => 45,
            'points_reward' => 1,
        ]);

        $tiemHotToc->services()->create([
            'name' => 'Nhuộm tóc',
            'description' => 'Nhuộm tóc theo kiểu',
            'should_notification' => true,
            'period' => 100,
            'points_reward' => 3,
        ]);

        $tiemHotToc->services()->create([
            'name' => 'Uốn tóc',
            'description' => 'Uốn tóc theo kiểu',
            'should_notification' => true,
            'period' => 365,
            'points_reward' => 3,
        ]);

        $tiemHotToc->services()->create([
            'name' => 'Làm móng',
            'description' => 'Làm móng theo kiểu',
            'should_notification' => true,
            'period' => 90,
            'points_reward' => 2,
        ]);

        $tiemHotToc->coupons()->create([
            'name' => 'Tặng chai keo vuốt tóc miễn phí',
            'description' => 'Tặng keo vuốt tóc miễn phí khi đủ 10 điểm',
            'require_point' => 10,
            'icon' => 'https://res.cloudinary.com/hmvv5mjf0/image/upload/v1715571636/uywh6pdsiqtiawmtmzpn.jpg',
            'is_active' => true,
            'expired_after' => 30,
        ]);

        $tiemHotToc->coupons()->create([
            'name' => 'Miễn phí 1 lần hớt tóc',
            'description' => 'Miễn phí 1 lần hớt tóc khi đủ 10 điểm',
            'require_point' => 10,
            'icon' => 'https://res.cloudinary.com/hmvv5mjf0/image/upload/v1715571636/uywh6pdsiqtiawmtmzpn.jpg',
            'is_active' => true,
            'expired_after' => 30,
        ]);

        $tiemSuaXe = Shop::factory()->create([
            'name' => 'Phúc Racing Shop',
            'description' => 'Tiệm sửa xe máy, xe đạp, xe hơi',
            'phone' => '0123456788',
            'address' => '255 Âu Dương Lân, Phường 2, Quận 8, TP.HCM',
            'logo' => 'https://res.cloudinary.com/hmvv5mjf0/image/upload/v1715094746/static/rcyjjdff2p0lujb36r4m.jpg',
            'cover' => 'https://res.cloudinary.com/hmvv5mjf0/image/upload/v1715094633/static/ej47jg4ikmlx6aylsdhy.jpg',
            'point_trigger' => PointMethod::ByVisit,
            'location' => [
                'type' => 'Point',
                'coordinates' => [
                    106.6845612,
                    10.7417873
                ]
            ]
        ]);

        $tiemSuaXe->user_id = $userManager2->_id;
        $tiemSuaXe->save();

        $tiemSuaXe->services()->create([
            'name' => 'Bảo trì, sửa chữa xe máy',
            'description' => 'Bảo trì, sửa chữa xe máy định kì 6 tháng 1 lần',
            'should_notification' => true,
            'period' => 180,
            'points_reward' => 3,
        ]);

        $tiemSuaXe->services()->create([
            'name' => 'Thay nhớt xe máy',
            'description' => 'Thay nhớt xe máy định kì 3 tháng 1 lần',
            'should_notification' => false,
            'period' => 90,
            'points_reward' => 1,
        ]);

        $tiemSuaXe->coupons()->create([
            'name' => 'Miễn phí thay nhớt xe máy',
            'description' => 'Miễn phí thay nhớt xe máy khi tích đủ 10 điểm',
            'require_point' => 10,
            'icon' => 'https://res.cloudinary.com/hmvv5mjf0/image/upload/v1715571636/uywh6pdsiqtiawmtmzpn.jpg',
            'is_active' => true,
            'expired_after' => 30,
        ]);

        $tiemCom = Shop::factory()->create([
            'name' => 'Cơm Sườn Vũ',
            'description' => 'Cơm bao ngon, không ngon không lấy tiền',
            'phone' => '0123456787',
            'address' => '373 An Dương Vương, Phường 3, Quận 5, TP.HCM',
            'logo' => 'https://res.cloudinary.com/hmvv5mjf0/image/upload/v1715094821/static/vwqrvvnt1klz6t78qtwe.jpg',
            'cover' => 'https://res.cloudinary.com/hmvv5mjf0/image/upload/v1715094633/static/ej47jg4ikmlx6aylsdhy.jpg',
            'point_trigger' => PointMethod::ByVisit,
            'location' => [
                'type' => 'Point',
                'coordinates' => [
                    106.6794701,
                    10.7605203
                ]
            ]
        ]);

        $tiemCom->user_id = $userManager3->_id;
        $tiemCom->save();

        $tiemCom->services()->create([
            'name' => 'Cơm sườn',
            'description' => 'Cơm sườn bao ngon, không ngon không lấy tiền',
            'should_notification' => true,
            'period' => 30,
            'points_reward' => 1,
        ]);

        $tiemCom->services()->create([
            'name' => 'Cơm gà',
            'description' => 'Cơm gà bao ngon, không ngon không lấy tiền',
            'should_notification' => true,
            'period' => 30,
            'points_reward' => 1,
        ]);

        $tiemCom->coupons()->create([
            'name' => 'Miễn phí 1 phần cơm sườn',
            'description' => 'Miễn phí 1 phần cơm sườn khi tích đủ 20 điểm',
            'require_point' => 20,
            'icon' => 'https://res.cloudinary.com/hmvv5mjf0/image/upload/v1715571636/uywh6pdsiqtiawmtmzpn.jpg',
            'is_active' => true,
            'expired_after' => 30,
        ]);


        $dienLanh = Shop::factory()->create([
            'name' => 'Điện lạnh Minh Tuấn',
            'description' => 'Dịch vụ sửa chữa điện lạnh, lắp đặt điều hòa, bảo trì máy lạnh',
            'phone' => '0123456788',
            'address' => '265 Nguyễn Trãi, Nguyễn Cư Trinh, Quận 1, TP.HCM',
            'logo' => 'https://res.cloudinary.com/hmvv5mjf0/image/upload/v1715094884/static/vmppojel7vuyvxlqaa0j.jpg',
            'cover' => 'https://res.cloudinary.com/hmvv5mjf0/image/upload/v1715094633/static/ej47jg4ikmlx6aylsdhy.jpg',
            'point_trigger' => PointMethod::ByVisit,
            'location' => [
                'type' => 'Point',
                'coordinates' => [
                    106.6865057,
                    10.7638081
                ]
            ]
        ]);

        $dienLanh->user_id = $userManager4->_id;
        $dienLanh->save();

        $dienLanh->services()->create([
            'name' => 'Sửa chữa máy lạnh',
            'description' => 'Sửa chữa máy lạnh, bảo trì máy lạnh',
            'should_notification' => true,
            'period' => 365,
            'points_reward' => 1,
        ]);

        $dienLanh->services()->create([
            'name' => 'Lắp đặt máy lạnh',
            'description' => 'Lắp đặt máy lạnh mới',
            'should_notification' => true,
            'period' => 365,
            'points_reward' => 3,
        ]);

        $dienLanh->coupons()->create([
            'name' => 'Miễn phí vệ sinh máy lạnh',
            'description' => 'Miễn phí vệ sinh máy lạnh khi tích đủ 15 điểm',
            'require_point' => 15,
            'icon' => 'https://res.cloudinary.com/hmvv5mjf0/image/upload/v1715571636/uywh6pdsiqtiawmtmzpn.jpg',
            'is_active' => true,
            'expired_after' => 30,
        ]);

    }
}
