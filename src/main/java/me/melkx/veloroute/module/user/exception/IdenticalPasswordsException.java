package me.melkx.veloroute.module.user.exception;

public class IdenticalPasswordsException extends RuntimeException {
    public IdenticalPasswordsException(String message) {
        super(message);
    }
}
