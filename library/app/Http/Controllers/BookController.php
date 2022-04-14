<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

//use App\Http\Resources\Book; 
use App\Models\Book;
use Illuminate\Support\Facades\Log;

use function PHPSTORM_META\map;

class BookController extends Controller
{
    // write a function called getBookIds that returns a JSON array of all the book ids
    public function getBookIds()
    {
        $bookIds = Book::all()->map(function ($book) {
            return $book->id;
        });

        return response()->json($bookIds);
    }

    // write a function called getBook that returns a book based on it's id in JSON format
    public function getBook($id)
    {
        $book = Book::find($id);

        return response()->json($book);
    }
    
    
   

    // write a function called deleteBook that deletes a book based on it's id in JSON format if there isnt any quantity of the book it will return a 404 error    
    public function deleteBook($id)
    {
        $book = Book::find($id);

        if (!$book) {
            return response()->json([
                "message" => "Book not found"
            ], 404);
        }

        $book->delete();

        return response()->json([
            "message" => "Book deleted"
        ], 200);
    }

    //write a function called createBook that creates a new book based on the JSON data sent in the request body and returns the new book in JSON format
    public function createBook(Request $request)
    {
        $fields = $request->validate([
            "author" => "required",
            "title" => "required",
            "quantity" => "required",
        ]);

        $book = Book::create($fields);

        return response()->json($book, 201);
    }
    

    //write a function called updateBook that updates a book based on it's id in JSON format if there isnt a book with the same id it will return a 404 error  
    public function updateBook(Request $request, $id)
    {
        $book = Book::find($id);

        if (!$book) {
            return response()->json([
                "message" => "Book not found"
            ], 404);
        }

        $book->update($request->all());

        return response()->json($book);
    }

}
