package com.nnk.springboot.exceptions;

public class InvalidUpdateUserException extends RuntimeException{
    public InvalidUpdateUserException(){
        super("Please enter a valid user");
    }
}
