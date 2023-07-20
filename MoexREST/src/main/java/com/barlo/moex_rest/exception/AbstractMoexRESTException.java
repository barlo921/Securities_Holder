package com.barlo.moex_rest.exception;

public abstract class AbstractMoexRESTException extends RuntimeException {
    public AbstractMoexRESTException(String message) {
        super(message);
    }
}
