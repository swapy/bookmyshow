package com.bookmy.errors.errors;

import java.net.URI;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class ConflictProblem extends AbstractThrowableProblem {

    static final URI TYPE = URI.create("/conflict");

    public ConflictProblem(String errorCode, String errorDetails) {
        super(TYPE, errorCode, Status.CONFLICT, errorDetails);
    }
}
