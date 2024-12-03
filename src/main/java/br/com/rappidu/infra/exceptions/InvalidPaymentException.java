package br.com.rappidu.infra.exceptions;

public class InvalidPaymentException extends RuntimeException{

    public InvalidPaymentException(String message) {
        super(message);
    }
}
