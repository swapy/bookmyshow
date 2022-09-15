package com.bookmy.errors.errors;

import java.net.URI;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class BadRequestProblem extends AbstractThrowableProblem {

    static final URI TYPE = URI.create("/badRequest");

    public BadRequestProblem(String errorCode, String errorDetails) {
        super(TYPE, errorCode, Status.BAD_REQUEST, errorDetails);
    }
}
