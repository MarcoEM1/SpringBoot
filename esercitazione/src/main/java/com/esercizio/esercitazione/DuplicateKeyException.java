package com.esercizio.esercitazione;

public class DuplicateKeyException extends Exception{
    public DuplicateKeyException(String errorMessage){
        super(errorMessage);
    }
}
