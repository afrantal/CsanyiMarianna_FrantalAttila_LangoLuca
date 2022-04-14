<?php

use App\Http\Controllers\AuthController;
use App\Http\Controllers\BookController;
use App\Http\Controllers\UserController;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\RentalController;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/


//Authetication
Route::post("/login",[AuthController::class , "login"]);
Route::post("/register",[AuthController::class , "register"]);


//Book
Route::middleware('auth:sanctum')->get("/bookIds",[BookController::class,"getBookIds"]);
Route::middleware('auth:sanctum')->get("/book/{id}",[BookController::class,"getBook"]);
Route::middleware('auth:sanctum')->post("/book",[BookController::class,"createBook"]);
Route::middleware('auth:sanctum')->put("/book/{id}",[BookController::class,"updateBook"]);
Route::middleware('auth:sanctum')->delete("/book/{id}",[BookController::class,"deleteBook"]);

// create endpoints for creating, updating, and deleting rentals
Route::middleware('auth:sanctum')->get("/rentals",[RentalController::class,"getRentedBooks"]);
Route::middleware('auth:sanctum')->post("/rental",[RentalController::class,"rentBook"]);
Route::middleware('auth:sanctum')->get("/rental/{id}",[RentalController::class,"getRental"]);
Route::middleware('auth:sanctum')->delete("/rental/{id}",[RentalController::class,"deleteRental"]);



Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    return $request->user();
});
