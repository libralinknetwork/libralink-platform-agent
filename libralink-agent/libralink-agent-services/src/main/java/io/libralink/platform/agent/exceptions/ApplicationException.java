package io.libralink.platform.agent.exceptions;

public class ApplicationException extends Exception {

    private final String code;

    public ApplicationException(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
