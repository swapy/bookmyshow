package com.bookmy.errors.errors;

import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

@Slf4j
public class InternalServerProblem extends AbstractThrowableProblem {

    static final URI TYPE = URI.create("/server-error");

    /**
     * We would not like customers to know what exactly went wrong at server level.
     * So we return static string internal server error
     * Actual message pointing to error is logged in next statement.
     *
     * @param errorCode actual error code
     * @param errorDetails actual error message.
     */
    public InternalServerProblem(String errorCode, String errorDetails) {
        super(TYPE, errorCode, Status.INTERNAL_SERVER_ERROR, "internal server error");
        log.error("Error occurred with errorCode {} and errorDetails {}",errorCode,errorDetails);
    }
}
