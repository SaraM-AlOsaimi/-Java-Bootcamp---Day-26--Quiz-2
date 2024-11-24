package com.example.exam2.Controller;

import com.example.exam2.ApiResponse.ApiResponse;
import com.example.exam2.Model.User;
import com.example.exam2.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.status(200).body(userService.getUsers());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addUser(@RequestBody @Valid User user , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added"));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable String id , @RequestBody @Valid User user , Errors errors){
      if(errors.hasErrors()){
          return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
      }
      boolean isUpdated = userService.updateUser(id,user);
      if(isUpdated){
          return ResponseEntity.status(200).body(new ApiResponse("User Updated"));
      }
      return ResponseEntity.status(400).body(new ApiResponse("ID not found"));

    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id){
        boolean isDeleted = userService.deleteUser(id);
        if (isDeleted){
            return ResponseEntity.status(200).body(new ApiResponse("User deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("id not found"));
    }


    @GetMapping("/users/by/balance/{balance}")
    public ResponseEntity<?> allUserByBalance(@PathVariable Integer balance){
        ArrayList usersByBalance = userService.allUserByBalance(balance);
        if(usersByBalance == null){
            return ResponseEntity.status(400).body(new ApiResponse("Not users found"));
        }
        return ResponseEntity.status(200).body(usersByBalance);
    }


    @GetMapping("/users/by/age/{age}")
    public ResponseEntity<?> getUsersByAge(@PathVariable Integer age){
        ArrayList usersByAge = userService.usersByAge(age);
        if(usersByAge == null){
            return ResponseEntity.status(400).body(new ApiResponse("No users found"));
        }
        return ResponseEntity.status(200).body(usersByAge);
    }

    @PutMapping("/change/{userID}/{bookID}")
    public ResponseEntity<?> changeBookStatus(@PathVariable String userID , @PathVariable String bookID){
        boolean isChanged = userService.changeBookStatus(userID,bookID);
        if(isChanged){
            return ResponseEntity.status(200).body(new ApiResponse("Book Status changed"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("oo"));
    }

}
