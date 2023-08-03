package com.nnk.springboot.exceptions;

public class InvalidUpdateRatingException extends RuntimeException{
    public InvalidUpdateRatingException(){
        super("this rating is invalid");
    }
}

