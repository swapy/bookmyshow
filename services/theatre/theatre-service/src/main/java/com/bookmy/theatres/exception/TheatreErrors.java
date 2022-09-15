package com.bookmy.theatres.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TheatreErrors {

    INVALID_SHOWID("SH001", "Invalid show id in request");

    private final String code;
    private final String message;
}
