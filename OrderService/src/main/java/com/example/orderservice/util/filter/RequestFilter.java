package com.example.orderservice.util.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.util.ContentCachingResponseWrapper;

import java.io.IOException;
import java.util.UUID;

@Slf4j
@Component
public class RequestFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        UUID uniqueId = UUID.randomUUID();
        MDC.put("requestId", uniqueId.toString());
        log.info("Request IP address is {}", servletRequest.getRemoteAddr());
        log.info("Request local address is {}", servletRequest.getLocalAddr());
        log.info("Request servlet connection is {}", servletRequest.getServletConnection());
        log.info("Request protocol is {}", servletRequest.getProtocol());
        log.info("Request id is {}", servletRequest.getRequestId());
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(
            httpServletResponse
        );
        filterChain.doFilter(servletRequest, responseWrapper);
        responseWrapper.setHeader("requestId", uniqueId.toString());
        responseWrapper.copyBodyToResponse();
        log.info("Response header is set with uuid {}", responseWrapper.getHeader("requestId"));
        log.info("Response contentType {}", servletResponse.getContentType());
        log.info("Response status {}", ((HttpServletResponse) servletResponse).getStatus());
        log.info("Response commit {}", servletResponse.isCommitted());
    }
}
