package com.example.exam2.Service;

import com.example.exam2.Model.Book;
import com.example.exam2.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserService {
    ArrayList<User> users = new ArrayList<>();


    public ArrayList<User> getUsers(){
        return users;
    }

    public void addUser(User user){
        users.add(user);
    }


    public boolean updateUser(String id , User user){
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(id)){
                users.set(i,user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String id){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)){
                users.remove(i);
                return true;
            }
        }
        return false;
    }
// Create an endpoint that takes a  balance then returns all users have this balance or above .

    public ArrayList<User> allUserByBalance(Integer balance){
        ArrayList<User> usersByBalance = new ArrayList<>();
        for (User user:users){
            if (user.getBalance() >= balance){
                usersByBalance.add(user);
            }
        }
        return (usersByBalance.isEmpty())?null:usersByBalance;
    }

    //Create an endpoint that takes an age then return all User who have this age  or above

    public ArrayList<User> usersByAge(Integer age){
        ArrayList<User> usersByAge = new ArrayList<>();
        for (User user: users){
            if(user.getAge() >= age){
                usersByAge.add(user);
            }
        }
        return (usersByAge.isEmpty())?null:usersByAge;
    }


    // Create an endpoint that change a book status to unavailable (Only the librarian can change the status of the book)
 
    public boolean changeBookStatus(String userID , String bookID){
         BookService bookService = null;
        ArrayList<Book> books = bookService.getBooks();
        for (User user : users){
            if(user.getId().equals(userID) && user.getRole().equals("librarian")){
                for (Book book : books){
                    if (book.getId().equals(bookID) && !book.isAvailable()){
                        book.setAvailable(false);
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
