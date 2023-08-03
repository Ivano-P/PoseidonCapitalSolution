package com.nnk.springboot.exceptions;

public class InvalidAddRatingException extends RuntimeException{
    public InvalidAddRatingException(){
        super("this rating is invalid");
    }
}
