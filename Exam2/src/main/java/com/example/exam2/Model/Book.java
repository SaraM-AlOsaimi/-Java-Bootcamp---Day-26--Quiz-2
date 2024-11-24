package com.example.exam2.Model;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Book {
    // Book Class :
    //      ID , name , number_of_pages , price  , category  , isAvailable
    //all should not be empty
    //category is novel OR academic
    @NotEmpty(message = "ID is empty")
    private String id;

    @NotEmpty(message = "Name is empty")
    private String name;

    @NotNull(message = "Number of pages is null")
    private Integer numberOfPages;

//    @NotEmpty(message = "Price is empty")

    @NotNull(message = "Price is empty")
    private Integer price;

    @NotEmpty(message = "Category is empty")
    @Pattern(regexp = "^novel|academic$" , message = "Category should be either : novel or academic")
    private String category;


    @AssertTrue(message = "Available must be true")
    private boolean isAvailable;

}
