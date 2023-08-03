package com.nnk.springboot.exceptions;

public class InvalidAddRuleNameException extends RuntimeException{
    public InvalidAddRuleNameException(){
        super("this is not a valid Rule Name");
    }
}
