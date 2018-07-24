package com.automation.ui.base.common.core.exceptions;

public class TestEnvInitFailedException extends RuntimeException {

    public TestEnvInitFailedException() {
        super("Failed to initialize test env");
    }

    public TestEnvInitFailedException(String message) {
        super(message);
    }

    public TestEnvInitFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
