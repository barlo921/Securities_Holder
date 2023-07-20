package com.barlo.moex_rest.exception;

public class NoResultsException extends AbstractMoexRESTException{
    public NoResultsException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
