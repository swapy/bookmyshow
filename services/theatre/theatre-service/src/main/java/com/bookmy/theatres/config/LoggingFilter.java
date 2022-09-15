package com.bookmy.theatres.config;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.stream.Stream;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Slf4j
@Component
public class LoggingFilter extends OncePerRequestFilter {

    private static final List<MediaType> VISIBLE_TYPES = Arrays.asList(
        MediaType.valueOf("text/*"),
        MediaType.APPLICATION_FORM_URLENCODED,
        MediaType.APPLICATION_PROBLEM_JSON,
        MediaType.APPLICATION_JSON,
        MediaType.APPLICATION_XML,
        MediaType.valueOf("application/*+json"),
        MediaType.valueOf("application/*+xml"),
        MediaType.MULTIPART_FORM_DATA
    );

    private static void logRequestHeader(ContentCachingRequestWrapper request, String prefix) {
        String queryString = request.getQueryString();
        if (queryString == null) {
            log.info("{} {} {}", prefix, request.getMethod(), request.getRequestURI());
        } else {
            log.info("{} {} {}?{}", prefix, request.getMethod(), request.getRequestURI(),
                queryString);
        }
        Collections.list(request.getHeaderNames()).forEach(headerName ->
            Collections.list(request.getHeaders(headerName))
                .forEach(headerValue -> log.info("{} {} {}", prefix, headerName, headerValue)));
        log.info("{}", prefix);
    }

    private static void logRequestBody(ContentCachingRequestWrapper request, String prefix) {
        byte[] content = request.getContentAsByteArray();
        if (content.length > 0) {
            logContent(content, request.getContentType(), request.getCharacterEncoding(), prefix);
        }
    }

    private static void logResponse(ContentCachingResponseWrapper response, String prefix) {
        int status = response.getStatus();
        log.info("{} {} {}", prefix, status, HttpStatus.valueOf(status).getReasonPhrase());
        response.getHeaderNames().forEach(header -> response.getHeaders(header)
            .forEach(headerValue -> log.info("{} {} {}", prefix, header, headerValue)));
        log.info("{}", prefix);
        byte[] content = response.getContentAsByteArray();
        if (content.length > 0) {
            logContent(content, response.getContentType(), response.getCharacterEncoding(), prefix);
        }
    }

    private static void logContent(byte[] content, String contentType, String contentEncoding,
        String prefix) {
        MediaType mediaType = MediaType.valueOf(contentType);
        boolean visible = VISIBLE_TYPES.stream()
            .anyMatch(visibleType -> visibleType.includes(mediaType));
        if (visible) {
            try {
                String contentString = new String(content, contentEncoding);
                Stream.of(contentString.split("\r\n|\r\n"))
                    .forEach(line -> log.info("{} {}", prefix, line));
            } catch (UnsupportedEncodingException e) {
                log.info("{}, [{} bytes content]", prefix, content.length);
            }
        } else {
            log.info("{}, [{} bytes content]", prefix, content.length);
        }
    }

    private static ContentCachingRequestWrapper wrapRequest(HttpServletRequest httpServletRequest) {
        if (httpServletRequest instanceof ContentCachingRequestWrapper req) {
            return req;
        } else {
            return new ContentCachingRequestWrapper(httpServletRequest);
        }
    }

    private static ContentCachingResponseWrapper wrapResponse(
        HttpServletResponse httpServletResponse) {
        if (httpServletResponse instanceof ContentCachingResponseWrapper res) {
            return res;
        } else {
            return new ContentCachingResponseWrapper(httpServletResponse);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse, FilterChain filterChain)
        throws ServletException, IOException {
        if (isAsyncDispatch(httpServletRequest)) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            doFilterWrapped(wrapRequest(httpServletRequest), wrapResponse(httpServletResponse),
                filterChain);
        }
    }

    protected void doFilterWrapped(ContentCachingRequestWrapper contentCachingRequestWrapper,
        ContentCachingResponseWrapper contentCachingResponseWrapper, FilterChain filterChain)
        throws IOException, ServletException {
        final String fallBackUUID = UUID.randomUUID().toString().replace("-", "");

        try {
            beforeRequest(contentCachingRequestWrapper,fallBackUUID);
            filterChain.doFilter(contentCachingRequestWrapper, contentCachingResponseWrapper);
        } finally {
            afterRequest(contentCachingRequestWrapper, contentCachingResponseWrapper,fallBackUUID);
            contentCachingResponseWrapper.copyBodyToResponse();
        }
    }

    protected void beforeRequest(ContentCachingRequestWrapper request,String fallBackUUID) {
        logRequestHeader(request, getCorrelationId(request,fallBackUUID) + "|>");
    }

    protected void afterRequest(ContentCachingRequestWrapper request,
        ContentCachingResponseWrapper response,String fallBackUUID) {
        logRequestBody(request, getCorrelationId(request,fallBackUUID));
        logResponse(response, getCorrelationId(request,fallBackUUID));
    }

    private String getCorrelationId(HttpServletRequest request,String fallbackUUID) {
        return "correlation-id : "+Optional.ofNullable(request.getHeader("X-Correlation-ID"))
            .orElse(fallbackUUID);
    }
}