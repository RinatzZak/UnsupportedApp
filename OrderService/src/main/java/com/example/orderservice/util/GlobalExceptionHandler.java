package com.example.orderservice.util;

import com.example.orderservice.util.exception.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Method that handles exception when entity wasn't found
     *
     * @param req request {@link HttpServletRequest}
     * @param ex  exception {@link EntityNotFoundException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorInfo> handleEntityNotFoundException(HttpServletRequest req, EntityNotFoundException ex) {
        return logAndGetErrorInfo(req, ex, ErrorType.ENTITY_NOT_FOUND);
    }

    /**
     * Method that handles application exceptions
     *
     * @param req request {@link HttpServletRequest}
     * @param ex  exception {@link EntityNotFoundException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handleAppException(HttpServletRequest req, Exception ex) {
        return logAndGetErrorInfo(req, ex, ErrorType.APP_ERROR);
    }

    /**
     * Method that handles Missing Servlet Request Parameter Exception
     *
     * @param req request {@link HttpServletRequest}
     * @param ex  exception {@link MissingServletRequestParameterException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorInfo> handleMissingServletRequestParameterException(HttpServletRequest req,
                                                                                   MissingServletRequestParameterException ex) {
        return logAndGetErrorInfo(req, ex, ErrorType.VALIDATION_EXCEPTION);
    }

    /**
     * Method that handles Http Message Not Readable Exception
     *
     * @param req request {@link HttpServletRequest}
     * @param ex  exception {@link HttpMessageNotReadableException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorInfo> handleHttpMessageNotReadableException(HttpServletRequest req,
                                                                           HttpMessageNotReadableException ex) {
        return logAndGetErrorInfo(req, ex, ErrorType.VALIDATION_EXCEPTION);
    }

    /**
     * Method that handles validation exception
     *
     * @param req request {@link HttpServletRequest}
     * @param ex  exception {@link EntityNotFoundException}
     * @return {@link ResponseEntity}
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> handleValidationException(HttpServletRequest req,
                                                               MethodArgumentNotValidException ex) {
        return logAndGetErrorInfo(req, ex, ErrorType.VALIDATION_EXCEPTION);
    }

    /**
     * Method that handles log and get exception
     *
     * @param req request {@link HttpServletRequest}
     * @return {@link ResponseEntity}
     */
    private ResponseEntity<ErrorInfo> logAndGetErrorInfo(HttpServletRequest req, Exception e, ErrorType errorType) {
        Throwable rootCause = Optional.ofNullable(NestedExceptionUtils.getRootCause(e)).orElse(e);
        log.error(errorType + " at request " + req.getRequestURL(), rootCause);
        return ResponseEntity.status(errorType.getStatus())
            .body(new ErrorInfo("/user" + req.getRequestURI(), errorType,
                e.getMessage(), LocalDateTime.now())
            );
    }

}
