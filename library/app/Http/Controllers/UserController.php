<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\User;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\Hash;


class UserController extends Controller
{
    //write a function called getUser to get a user's id, name, email, telephone and password
    public function getUser(Request $request)
    {
        $user = Auth::user();
        return response()->json($user, 200);
    }
    

    
    //write a function called getName that returns the name of the user in json format

    public function getName()
    {
        $name = Auth::user()->name;

        return response()->json($name);
    }
}