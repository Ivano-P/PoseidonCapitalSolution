package com.nnk.springboot.exceptions;

public class InvalidUpdateRuleNameException extends RuntimeException{
    public InvalidUpdateRuleNameException(){
        super("this is not a valid Rule Name");
    }
}
