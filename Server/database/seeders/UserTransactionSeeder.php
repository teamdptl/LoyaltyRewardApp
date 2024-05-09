<?php

namespace Database\Seeders;

use App\Http\Controllers\ShopController;
use App\Http\Controllers\UserController;
use App\Models\User;
use Illuminate\Database\Console\Seeds\WithoutModelEvents;
use Illuminate\Database\Seeder;
use Illuminate\Http\Request;

class UserTransactionSeeder extends Seeder
{
    /**
     * Run the database seeds.
     */
    public function run(): void
    {
        // Tạo danh sách tích điểm
        $shopOwner = User::find('663a4e90d3b422b0fe0e2352');
        $customer = User::find('662fc03229cf8e79fe0b3882');
//        $list_services_id = ['663a4e93d3b422b0fe0e2357', '663a4e93d3b422b0fe0e2358', '663a4e93d3b422b0fe0e235a',
//            '663a4e93d3b422b0fe0e2359', '663a4e93d3b422b0fe0e2357', '663a4e93d3b422b0fe0e2358', '663a4e93d3b422b0fe0e2358',
//            '663a4e93d3b422b0fe0e2358', '663a4e93d3b422b0fe0e2358', '663a4e93d3b422b0fe0e2358'];
//
//        // loop through list_services
//        foreach ($list_services_id as $service){
//            $request = new Request();
//            $request->merge([
//                'user_id' => $customer->_id,
//                'service_id' => $service
//            ]);
//            $request->user = $shopOwner;
//            $shopController = new ShopController();
//            $shopController->scanQR($request);
//        }

        // Tạo danh sách đổi coupons
//        $list_coupons_id = ['663a4e93d3b422b0fe0e235c'];
//        foreach ($list_coupons_id as $coupon){
//            $request = new Request();
//            $request->merge([
//                'coupon_id' => $coupon
//            ]);
//            $request->user = $customer;
//            $userController = new UserController();
//            $userController->exchangeCoupon($request);
//        }

        // Tạo dữ liệu đổi quà tại cửa hàng
        $list_rewards_id = $customer->coupons;
        foreach ($list_rewards_id as $reward){
            $request = new Request();
            $request->merge([
                'user_id' => $customer->_id,
                'reward_id' => $reward->_id
            ]);
            $request->user = $shopOwner;
            $shopController = new ShopController();
            $shopController->scanQR($request);
        }


    }
}
