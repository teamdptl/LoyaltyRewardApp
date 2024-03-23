<?php

namespace App\Http\Controllers;

use App\Models\Post;
use Illuminate\Http\Request;

class PostController extends Controller
{
    public function show($title)
    {
        return Post::where('_id', '=', $title)->first();
    }

    public function store(Request $request)
    {
        $post = new Post;

        $post->title = "2";
        $post->body = "Tuan dep trai";

        $post->save();

        return $post;
    }
}
