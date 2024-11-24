package com.example.exam2.Controller;

import com.example.exam2.ApiResponse.ApiResponse;
import com.example.exam2.Model.Book;
import com.example.exam2.Service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/get")
    public ResponseEntity<?> getBooks(){
       return ResponseEntity.status(200).body(bookService.getBooks());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody @Valid Book book , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        bookService.addBook(book);
        return ResponseEntity.status(200).body(new ApiResponse("Book added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBook(@PathVariable String id , @RequestBody @Valid Book book , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isUpdate = bookService.updateBook(id,book);
        if(isUpdate){
            return ResponseEntity.status(200).body(new ApiResponse("Book Updated Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("ID not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id){
        boolean isFound = bookService.deleteBook(id);
        if(isFound){
            return ResponseEntity.status(200).body(new ApiResponse("Book deleted"));

        }
        return ResponseEntity.status(400).body(new ApiResponse("ID not found"));
    }

    @GetMapping("/get/book/{name}")
    public ResponseEntity<?> getOneBook(@PathVariable String name){
        Book book = bookService.getOneBook(name);
        if(book != null){
            return ResponseEntity.status(200).body(book);
        }
        return ResponseEntity.status(400).body(new ApiResponse("Book not found"));
    }


    @GetMapping("/by/category/{category}")
    public ResponseEntity<?> getBooksByCategory(@PathVariable String category){
        ArrayList booksByCategory = bookService.getBooksByCategory(category);
        if(booksByCategory == null){
            return ResponseEntity.status(400).body(new ApiResponse("No books found,Category should be either : novel or academic"));

        }
        return ResponseEntity.status(200).body(booksByCategory);
    }

    @GetMapping("/get/{numberOfPages}")
    public ResponseEntity<?> getBooksByPageNumber(@PathVariable Integer numberOfPages){
        ArrayList booksByPageNumber = bookService.getBooksByPagesNumber(numberOfPages);
        if(booksByPageNumber == null){
            return ResponseEntity.status(400).body(new ApiResponse("No books found "));
        }
        return ResponseEntity.status(200).body(booksByPageNumber);
    }


//    @PutMapping("/change/{userID}/{bookID}")
//    public ResponseEntity<?> changeBookStatus(@PathVariable String userID , @PathVariable String bookID){
//       boolean isStatusChanged = bookService.changeBookStatus(userID,bookID);
//        if (isStatusChanged){
//            return ResponseEntity.status(200).body(new ApiResponse("Book status changed "));
//        }
//        return ResponseEntity.status(400).body(new ApiResponse("--"));
//    }

}
