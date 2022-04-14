<?php
namespace App\Http\Controllers;
use App\Models\Rental;
use App\Models\Book;
use App\Models\User;
use App\Http\Resources\Rental as RentalResource;
use App\Models\Rentals as ModelsRentals;
use Illuminate\Http\Request;


class RentalController extends Controller
{
    
    //write a function that will return all rentals 
    public function getRentedBooks()
    {
        $rentals = Rental::all();
        return RentalResource::collection($rentals);
    }

    // write a function that handles rentals.
    // It should receive the book id and the user id and the quantity of the book to rent or give back.
    // It should add the rental quantity to the quantity of the book.
    // If the library does not have enough books, it should return a 400 error.
    // THe books rented by the user should be stored in the rentals table.
    // If the user doesn't have enough books, it should return a 400 error.
    // If the book doesn't exist, it should return a 400 error.
    // If the user doesn't exist, it should return a 400 error.
    // If all the books are already rented, it should return a 400 error.
    // Return the error message in JSON format alongside with the error code.
    public function rentBook(Request $request)
    {
        $book = Book::find($request->book_id);

        if (!$book) {
            return response()->json([
                "message" => "Book not found"
            ], 404);
        }

        $user = User::find($request->user_id);
        if(!$user) {
            return response()->json([
                "message" => "User not found"
            ], 404);
        }

        $rental = Rental::where('book_id', $request->book_id)->where('user_id', $request->user_id)->first();
        if (!$rental) { // it's a new rental by this user

            // if requested quantity is positive, the user can't return the book he hasn't rented yet. Return an error.
            if ($request->quantity > 0) {
                return response()->json([
                    "message" => "You haven't rented this book yet."
                ], 400);
            } else if($request->quantity == 0) {
                return response()->json([
                    "message" => "You can't rent zero books."
                ], 400);
            }

            if ($book->quantity >= -$request->quantity) {
                $book->quantity += $request->quantity;
                $book->save();
                $rental = Rental::create([
                    'user_id' => $request->user_id,
                    'book_id' => $request->book_id,
                    'quantity' => -$request->quantity,
                ]);
                return response()->json(["message" => "You have succesfully rented this book"], 200);
            }
            return response()->json([
                "message" => "Not enough books in the library"
            ], 400);
        }else {
            // if requested quantity is negative
            if ($request->quantity < 0) {
                return response()->json([
                    "message" => "Book already rented. Return the previously rented ".$rental->quantity." book(s) first!"
                ], 400);
            }else if($request->quantity > 0) {
                // if requested quantity is positive
                if ($rental->quantity >= $request->quantity) {
                    $book->quantity += $request->quantity;
                    $book->save();
                    $rental->quantity -= $request->quantity;
                    $rental->save();

                    if($rental->quantity == 0) {
                        $rental->delete();
                    }
                    return response()->json(new RentalResource($rental), 200);
                }
                return response()->json([
                    "message" => "You havent rented this many books yet"
                ], 400);
            }else {
                // if requested quantity is zero, return an error
                return response()->json([
                    "message" => "You cant return/request a book with a quantity of 0"
                ], 400);
            }
        }
    }

    //write a function that will return a rental based on it's id
    public function getRental($id)
    {
        $rental = Rental::find($id);
        return response()->json(new RentalResource($rental), 200);
    }

    //write a function that will delete a rental based on it's id
    public function deleteRental($id)
    {
        $rental = Rental::find($id);
        if (!$rental) {
            return response()->json([
                "message" => "Rental not found"
            ], 404);
        }
        $rental->delete();
        return response()->json([
            "message" => "Rental deleted"
        ], 200);
    } 
    
}
