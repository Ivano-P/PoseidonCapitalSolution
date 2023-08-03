package com.nnk.springboot.exceptions;

public class InvalidUpdateCurvePointException extends RuntimeException{
    public InvalidUpdateCurvePointException(){
        super("Curve id has to be a whole number");
    }
}
