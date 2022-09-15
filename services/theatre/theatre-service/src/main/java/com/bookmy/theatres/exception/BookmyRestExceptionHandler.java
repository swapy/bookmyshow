package com.bookmy.theatres.exception;

import static org.zalando.problem.Status.INTERNAL_SERVER_ERROR;

import com.bookmy.errors.errors.BadRequestProblem;
import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.zalando.problem.Problem;
import org.zalando.problem.ThrowableProblem;
import org.zalando.problem.spring.web.advice.ProblemHandling;

@RestControllerAdvice
@Slf4j
public class BookmyRestExceptionHandler implements ProblemHandling {

    @Override
    public ResponseEntity<Problem> handleTypeMismatch(TypeMismatchException exception,
        NativeWebRequest request) {
        String propertyName = ((MethodArgumentTypeMismatchException) exception).getName();
        var problem = new BadRequestProblem(ErrorType.SE003.name(),
            "Invalid Input For Property " + propertyName);
        return this.create(problem, request);
    }

    /**
     * Ensure you do not leak database exceptions to frontend https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#dao-exceptions
     */
    @Order
    @ExceptionHandler({DataAccessException.class, TransactionException.class})
    ResponseEntity<Problem> handleAllUnhandledDataExceptions(Exception ex) {
        log.error("Logging Data Exception Unhandled At Backend : " + ex);
        final Problem problem = getServerError(ErrorType.SE002);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem);
    }

    /**
     * Ensure you do not leak any exceptions that are not handled to frontend. This will map all
     * remaining runtime exceptions to this final exception handler.
     */
    @Order
    @ExceptionHandler(Exception.class)
    ResponseEntity<Problem> handleAllUnhandledBackendExceptions(Exception ex) {
        log.error("Logging Unhandled Exception In Backend : " + ex);
        final Problem problem = getServerError(ErrorType.SE001);

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problem);
    }

    private ThrowableProblem getServerError(ErrorType errorType) {
        return Problem.builder()
            .withType(URI.create("/server/error"))
            .withTitle(errorType.name())
            .withDetail("server error")
            .withStatus(INTERNAL_SERVER_ERROR)
            .build();
    }


    /**
     * Description is for developers to understand what happened. Do not expose it to client
     */
    enum ErrorType {

        SE001("Unknown Server Error"),
        SE002("Database Level Error"),
        SE003("Invalid Parameter Input By Client For Property");

        private String description;

        ErrorType(String description) {
            this.description = description;
        }
    }
}