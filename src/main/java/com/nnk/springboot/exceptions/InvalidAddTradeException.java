package com.nnk.springboot.exceptions;

public class InvalidAddTradeException extends RuntimeException{
    public InvalidAddTradeException(){
        super("Please enter a valid trade");
    }

}
