package io.libralink.platform.agent.exceptions;

public class EnvelopeNotFoundException extends ApplicationException {

    public EnvelopeNotFoundException(String message) {
        super(message, "envelope_not_found");
    }
}
