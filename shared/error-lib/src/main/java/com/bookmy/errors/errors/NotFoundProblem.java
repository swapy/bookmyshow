package com.bookmy.errors.errors;

import java.net.URI;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class NotFoundProblem extends AbstractThrowableProblem {

    static final URI TYPE = URI.create("/not-found");

    public NotFoundProblem(String errorCode, String errorDetails) {
        super(TYPE, errorCode, Status.NOT_FOUND, errorDetails);
    }
}
