package io.rafat.expensetracker.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.io.IOException;

@Component
@Slf4j
@Order(2)
public class ErrorLoggingFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);
        StatusCaptureWrapper wrappedResponse = new StatusCaptureWrapper(response);

        try {
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            int status = wrappedResponse.getStatus();
            if (status >= 400) {
                String method = request.getMethod();
                String path = request.getRequestURI();
                String query = request.getQueryString();

                String body = extractBody(wrappedRequest);

                log.error("{} {} {} {} {} ", method, path, query, body, status);
            }
        }
    }

    private String extractBody(ContentCachingRequestWrapper request) {
        byte[] buf = request.getContentAsByteArray();
        if (buf.length == 0) return null;

        try {
            return new String(buf, 0, buf.length, request.getCharacterEncoding());
        } catch (Exception e) {
            return "⚠️ [UNREADABLE BODY]";
        }
    }
}
