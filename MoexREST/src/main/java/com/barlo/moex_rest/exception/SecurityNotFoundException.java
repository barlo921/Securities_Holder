package com.barlo.moex_rest.exception;

public class SecurityNotFoundException extends AbstractMoexRESTException{
    public SecurityNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
