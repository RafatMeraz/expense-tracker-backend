package io.rafat.expensetracker.config;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import lombok.Getter;

import java.io.IOException;

@Getter
public class StatusCaptureWrapper extends HttpServletResponseWrapper {
    private int httpStatusCode = 200;

    public StatusCaptureWrapper(HttpServletResponse response) {
        super(response);
    }

    @Override
    public void setStatus(int sc) {
        super.setStatus(sc);
        this.httpStatusCode = sc;
    }

    @Override
    public void sendError(int sc, String msg) throws IOException {
        super.sendError(sc, msg);
        this.httpStatusCode = sc;
    }

}
