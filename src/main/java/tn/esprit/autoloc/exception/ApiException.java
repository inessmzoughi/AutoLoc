package tn.esprit.autoloc.exception;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}