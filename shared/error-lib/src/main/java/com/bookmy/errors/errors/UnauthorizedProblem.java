package com.bookmy.errors.errors;

import java.net.URI;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class UnauthorizedProblem extends AbstractThrowableProblem {

    static final URI TYPE = URI.create("/unauthorized");

    public UnauthorizedProblem(String errorCode, String errorDetails) {
        super(TYPE, errorCode, Status.UNAUTHORIZED, errorDetails);
    }
}
