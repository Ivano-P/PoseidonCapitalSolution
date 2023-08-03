package com.nnk.springboot.exceptions;

public class InvalidAddBidListException extends RuntimeException{
    public InvalidAddBidListException(){
        super("BidList must have a valid Account, Type and bid Quantity");}
}
