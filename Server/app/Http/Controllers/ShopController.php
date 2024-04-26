<?php
namespace App\Http\Controllers;

use App\Http\Services\AuthService;
use App\Models\Shop;
use App\Models\User;
use Illuminate\Http\Request;
use Symfony\Component\HttpFoundation\Response;

class ShopController extends Controller{
    // TODO: API thứ 2 - Đề xuất cho user các shop gần nhất
    public function index(){
        return Shop::all();
    }

    /**
     * 16 - Xem. Xem shop cho manager
     *
     * Thực hiện xem shop
     *
     */
    public function show(Request $request){
        if (!$request->user->shop){
            return Response("User chưa có shop!", 400);
        }
        return $request->user->shop;
    }

    /**
     * 16 - Tạo. Tạo shop cho manager
     *
     * Thực hiện tạo shop với các thuộc tính như tên, mô tả, địa chỉ, điện thoại, logo, cover, cách tính điểm (point trigger), vĩ độ, kinh độ
     *
     */
    public function store(Request $request){
        if ($request->user->shop){
            return Response("User đã có shop!", 400);
        }

        $validate = $request->validate([
            'name' => 'required|string',
            'description' => 'nullable|string',
            'address' => 'required|string',
            'phone' => 'nullable|regex:/(0)[0-9]{9}/',
            'logo' => 'required|file|image|max:20000',
            'cover' => 'nullable|file|image|max:20000',
            'point_trigger' => 'nullable|string',
            'latitude' => 'nullable|numeric',
            'longitude' => 'nullable|numeric'
        ]);

        //Lưu file logo và cover vào storage
        if ($request->hasFile('logo')) {
            $logo_path = cloudinary()->upload($request->file('logo')->getRealPath())->getSecurePath();
            $validate['logo'] = $logo_path;
        }

        if ($request->hasFile('cover')) {
            $cover_path = cloudinary()->upload($request->file('cover')->getRealPath())->getSecurePath();
            $validate['cover'] = $cover_path;
        }

        $shop = new Shop($validate);

        //Có thể dùng 1 trong 2 cách bên dưới để lưu shop có relate to user
        $request->user->shop()->save($shop);

        return Response("Tạo shop thành công!", 200);
    }

    /**
     * 16 - Sửa. Sửa shop cho manager
     *
     * Thực hiện sửa shop với các thuộc tính như tên, mô tả, địa chỉ, điện thoại, logo, cover, cách tính điểm (point trigger), vĩ độ, kinh độ
     *
     */
    public function update(Request $request){
        $shop = $request->user->shop;
        if (!$shop){
            return Response("Không tìm thấy shop!", 404);
        }

        $validate = $request->validate([
            'name' => 'required|string',
            'description' => 'nullable|string',
            'address' => 'required|string',
            'phone' => 'nullable|regex:/(0)[0-9]{9}/',
            'logo' => 'required|file|image|max:20000',
            'cover' => 'nullable|file|image|max:20000',
            'point_trigger' => 'nullable|string',
            'latitude' => 'nullable|numeric',
            'longitude' => 'nullable|numeric'
        ]);

        if ($request->hasFile('logo')) {
            $logo_path = cloudinary()->upload($request->file('logo')->getRealPath())->getSecurePath();
            $validate['logo'] = $logo_path;
        }

        if ($request->hasFile('cover')) {
            $cover_path = cloudinary()->upload($request->file('cover')->getRealPath())->getSecurePath();
            $validate['cover'] = $cover_path;
        }

        $shop->fill($validate);
        $shop->save();

        return Response("Cập nhật thành công", 200);
    }

    /**
     * 16 - Xóa. Xóa shop cho manager
     *
     * Thực hiện xóa shop của user hiện tại
     *
     */
    public function destroy(Request $request)
    {
        $shop = $request->user->shop;
        if (!$shop) {
            return Response("Không tìm thấy shop!", 404);
        }

        $shop->delete();
        return Response("Xóa shop thành công!", 200);
    }


    /**
     * 8. Lấy thông tin cửa hàng theo id
     *
     * Dữ liệu trả về gồm các ưu đãi, dịch vụ, mô tả cửa hàng (dùng ở trang chi tiết cửa hàng)
     *
     */
    public function getShopById($id){
        $shop = Shop::find($id);
        if($shop){
            $shop->load('coupons', 'services');
            return $shop;
        }
        return Response('Không tìm thấy shop này!', 404);
    }

}
