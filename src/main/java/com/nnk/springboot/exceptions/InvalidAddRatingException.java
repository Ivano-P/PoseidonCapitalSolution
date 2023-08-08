package com.nnk.springboot.exceptions;

public class InvalidAddRatingException extends RuntimeException{
    public InvalidAddRatingException(){
        super("there must be at the least one type of rating");
    }
}
