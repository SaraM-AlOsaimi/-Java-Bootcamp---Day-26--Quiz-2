package com.example.exam2.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {

// User Class :
//    ID , name , age ,  balance , role
//    1. all should not be empty
//    2. balance should be a valid number
//    3. role is   customer OR libraian
    @NotEmpty(message = "Id is empty")
    private String id;

    @NotEmpty(message = "Name is empty")
    private String name;

    @NotNull(message = "age is null")
    private Integer age;

//    @NotEmpty(message = "Balance is empty")
    @NotNull(message = "balance is null")
    @Positive(message = "Balance must be a valid number")
    private Integer balance;


    @NotEmpty(message = "Role is empty")
    @Pattern(regexp = "^customer|librarian$", message = "Role must be : customer or libreian")
    private String role;


}
