package com.MarketPet.MarketPet.Exception;

public class RelatorioException extends RuntimeException {
    public RelatorioException(String message) {
        super(message);
    }

    public RelatorioException(String message, Throwable cause) {
        super(message, cause);
    }
}