package com.nnk.springboot.exceptions;

public class InvalidBidListException extends RuntimeException{
    public InvalidBidListException(){
        super("BidList must have a valid Account, Type and bid Quantity");}
}
