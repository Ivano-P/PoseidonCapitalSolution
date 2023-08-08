package com.nnk.springboot.exceptions;

public class InvalidUpdateRatingException extends RuntimeException{
    public InvalidUpdateRatingException(){
        super("there must be at the least one type of rating");
    }
}

