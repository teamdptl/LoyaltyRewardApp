<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use App\Http\Services\AuthService;
use App\Models\Service;
use App\Models\User;
use Illuminate\Http\Request;
use Kreait\Firebase\Contract\Auth;
use Symfony\Component\HttpFoundation\Response;

/**
 * @tags Dịch vụ
 */

class ServiceController extends Controller
{

    public function index(){
        return Service::all();
    }

    public function show($id){
        return Service::find($id);
    }


    /**
     * 19 - Tạo. Tạo dịch vụ cho shop
     *
     * Tạo dịch vụ với các thông tin như tên, mô tả, nhắc thông báo, số điểm cần có, icon, trạng thái kích hoạt
     *
     */
    public function store(Request $request){

        $validated = $request->validate(
            [
                'name' =>'required|string|max:255',
                'description' =>'required|string|max:1000',
                'should_notification' =>'required|boolean',
                'period' => 'required|integer|min:1',
                'points_reward' => 'required|integer|min:0',
            ]
        );

        $shop = $request->user->shop;
        if(!$shop){
            return Response("Bạn chưa có shop, vui lòng tạo mới", 404);
        }

        $service = new Service($validated);
        $shop->services()->save($service);
        return Response('Tạo dịch vụ thành công!', 200);
    }

    /**
     * 19 - Sửa. Sửa dịch vụ cho shop
     *
     * Sửa dịch vụ với các thông tin như tên, mô tả, nhắc thông báo, số điểm cần có, icon, trạng thái kích hoạt
     *
     */
    public function update(Request $request, string $id)
    {
        $validated = $request->validate(
            [
                'name' => 'required|string|max:255',
                'description' => 'required|string|max:1000',
                'should_notification' => 'required|boolean',
                'period' => 'required|integer|min:1',
                'points_reward' => 'required|integer|min:0',
            ]
        );

        $shop = $request->user->shop;
        if (!$shop) {
            return Response("Bạn chưa có shop, vui lòng tạo mới", 404);
        }

        $service = Service::find($id);
        if (!$service || $service->shop_id != $shop->id) {
            return Response("Dịch vụ không tồn tại", 404);
        }

        $service->update($validated);
        return Response('Cập nhật dịch vụ thành công!', 200);
    }

    /**
     * 19 - Xóa. Xóa dịch vụ cho shop
     *
     * Xóa dịch vụ với id
     *
     */
    public function destroy(Request $request, string $id){
        $shop = $request->user->shop;
        if(!$shop){
            return Response('Không tìm thấy cửa hàng!', 404);
        }

        $service = Service::find($id);
        if(!$service || $service->shop_id != $shop->id){
            return Response('Không tìm thấy dịch vụ!', 404);
        }

        $service->delete();
        return Response('Xóa dịch vụ thành công!', 200);
    }

}
