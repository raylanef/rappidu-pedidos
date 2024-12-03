package br.com.rappidu.infra.exceptions;

public class OrderNotFountException extends RuntimeException {

    public OrderNotFountException(String message) {
        super(message);
    }
}
