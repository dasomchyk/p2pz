package com.stormnet.client.exceptions;

public class LoginIsAlreadySetException extends RuntimeException {

    public LoginIsAlreadySetException() {

        super("This login is not available.");
    }
}
