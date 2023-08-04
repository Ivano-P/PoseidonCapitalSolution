package com.nnk.springboot.exceptions;

public class InvalidUpdateTradeException extends RuntimeException{
    public InvalidUpdateTradeException(){
        super("Please enter a valid trade");
    }
}
