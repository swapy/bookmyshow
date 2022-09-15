package com.bookmy.errors.errors;

import java.net.URI;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class ForbiddenProblem extends AbstractThrowableProblem {

    static final URI TYPE = URI.create("/forbidden");

    public ForbiddenProblem(String errorCode, String errorDetails) {
        super(TYPE, errorCode, Status.FORBIDDEN, errorDetails);
    }
}
