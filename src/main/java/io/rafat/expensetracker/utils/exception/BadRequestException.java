package io.rafat.expensetracker.utils.exception;

import io.rafat.expensetracker.utils.constant.CommonMessages;

public class BadRequestException extends RuntimeException {
   public BadRequestException() {
        super(CommonMessages.BAD_REQUEST_ERROR);
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
