package com.nnk.springboot.exceptions;

public class InvalidAddCurvePointException extends RuntimeException{
    public InvalidAddCurvePointException(){
        super("Curve id has to be a whole number");
    }
}
