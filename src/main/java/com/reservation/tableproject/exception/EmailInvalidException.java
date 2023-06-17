package com.reservation.tableproject.exception;

public class EmailInvalidException extends RuntimeException{
    public EmailInvalidException(String message){
        super(message);
    }
}
