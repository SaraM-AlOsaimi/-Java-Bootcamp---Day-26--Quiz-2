package com.example.exam2.Service;

import com.example.exam2.Model.Book;
import com.example.exam2.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BookService {

    ArrayList<Book> books = new ArrayList<>();

    // get
    public ArrayList<Book> getBooks(){
        return books;
    }

public void addBook(Book book){
  books.add(book);
}

public boolean updateBook(String id , Book book){
    for (int i = 0; i < books.size(); i++) {
        if(books.get(i).getId().equals(id)){
            books.set(i,book);
            return true;
        }
    }
    return false;
}

public boolean deleteBook(String id){
    for (int i = 0; i < books.size(); i++) {
        if(books.get(i).getId().equals(id)){
            books.remove(i);
            return true;
        }
    }
    return false;
}

// Create an endpoint that takes a Book name then returns one Book .
    public Book getOneBook(String name){
        for (int i = 0; i < books.size(); i++) {
            if(books.get(i).getName().equals(name)){
                return books.get(i);
            }
        }
        return null;
    }

    // Create an endpoint that takes a category then return all books have this category.

    public ArrayList<Book> getBooksByCategory(String category){
        ArrayList<Book> booksByCategory = new ArrayList<>();
        for (Book book : books){
            if(book.getCategory().equals(category)){
                booksByCategory.add(book);
            }
        }
        return (booksByCategory.isEmpty())?null:booksByCategory;
    }

// Create an endpoint that takes a number of pages and returns all Books who have this number of pages or above .
    public ArrayList<Book> getBooksByPagesNumber(Integer numberOfPages){
        ArrayList<Book> booksByPages = new ArrayList<>();
        for (Book book : books){
            if(book.getNumberOfPages() >= numberOfPages){
                booksByPages.add(book);
            }
        }
        return (booksByPages.isEmpty())?null:booksByPages;
    }



//    private UserService userService;
//    // Create an endpoint that change a book status to unavailable (Only the librarian can change the status of the book)
//    public boolean changeBookStatus(String userID , String bookID){
//      for (User user : userService.getUsers()){
//          if(user.getId().equals(userID) && user.getRole().equals("librarian")){
//              for (Book book : books){
//                  if (book.getId().equals(bookID) && !book.isAvailable()){
//                      book.setAvailable(false);
//                      return true;
//                  }
//              }
//          }
//      }
//      return false;
//    }


}
