package io.rafat.expensetracker.utils.exception;

import io.rafat.expensetracker.utils.constant.CommonMessages;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException() {
        super(CommonMessages.ALREADY_EXISTS_ERROR);
    }

    public AlreadyExistsException(String message) {
        super(message);
    }
    public AlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
    public AlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
