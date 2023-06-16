package com.reservation.tableproject.exception;

public class PartnerAlreadyExistException extends RuntimeException{
    public PartnerAlreadyExistException(String message){
        super(message);
    }
}
