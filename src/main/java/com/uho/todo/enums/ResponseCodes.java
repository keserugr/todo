package com.uho.todo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCodes {
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST,"User not found"),
    TICKET_NOT_FOUND(HttpStatus.BAD_REQUEST, "Ticket not found"),
    SUCCESS(HttpStatus.ACCEPTED,"Success");

    private final HttpStatus httpStatus;
    private final String message;

}
