<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class FileController extends Controller
{
    /**
     * Upload file ảnh lên cloudinary
     *
     * Trả về url sau khi upload
     */
    public function upload(Request $request)
    {
        $request->validate([
            'file' => 'required|file|mimes:jpeg,png,jpg,gif,svg|max:10000',
        ]);

        $upload_path = cloudinary()->upload($request->file('file')->getRealPath())->getSecurePath();
        return response()->json(['upload_path' => $upload_path]);
    }
}
