package io.rafat.expensetracker.utils.exception;

import io.rafat.expensetracker.utils.constant.CommonMessages;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super(CommonMessages.NOT_FOUND_ERROR);
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
