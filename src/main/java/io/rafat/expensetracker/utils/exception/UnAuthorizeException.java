package io.rafat.expensetracker.utils.exception;

import io.rafat.expensetracker.utils.constant.CommonMessages;

public class UnAuthorizeException extends RuntimeException {
    private String message;

    public UnAuthorizeException() {
        this.message = CommonMessages.UNAAUTHORIZED_ERROR;
    }

    public UnAuthorizeException(String message) {
        this.message = message;
    }

    public UnAuthorizeException(String message, Throwable cause) {
        super(message, cause);
    }
}
