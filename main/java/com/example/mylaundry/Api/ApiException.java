package com.example.mylaundry.Api;

public class ApiException extends RuntimeException{
    public ApiException (String massage){
        super(massage);
    }
}
