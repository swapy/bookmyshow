package com.bookmy.errors.errors;

import java.net.URI;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class UnprocessableEntityProblem extends AbstractThrowableProblem {

    static final URI TYPE = URI.create("/unprocessable-entity");

    public UnprocessableEntityProblem(String errorCode, String errorDetails) {
        super(TYPE, errorCode, Status.UNPROCESSABLE_ENTITY, errorDetails);
    }
}
