package br.com.alura.loja.exceptions;

public class ErrorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ErrorException(String message) {
        super(message);
    }
}
