package com.nnk.springboot.exceptions;

public class InvalidAddUserException extends RuntimeException{
    public InvalidAddUserException(){
        super("Please enter a valid user");
    }
}
