package com.nnk.springboot.exceptions;

public class InvalidUpdateBidListException extends RuntimeException{
    public InvalidUpdateBidListException(){
        super("BidList must have a valid Account, Type and bid Quantity");}
}
