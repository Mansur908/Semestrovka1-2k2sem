package ru.itis.demo.exceptions;


public class LoginProcessErrorException extends Exception{
    public LoginProcessErrorException(String message) {
        super(message);
    }
}