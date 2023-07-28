package com.barlo.investment_portfolio.exception;

public abstract class AbstractInvestmentException extends RuntimeException{
    public AbstractInvestmentException(String message) {
        super(message);
    }
}
