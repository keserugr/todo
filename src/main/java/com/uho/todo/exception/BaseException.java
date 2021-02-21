package com.uho.todo.exception;

import com.uho.todo.enums.ResponseCodes;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException{

    private final ResponseCodes responseCodes;

    public BaseException(ResponseCodes responseCodes) {
        super(responseCodes.getMessage());
        this.responseCodes = responseCodes;
    }

    public BaseException(ResponseCodes responseCodes, String message) {
        super(message);
        this.responseCodes = responseCodes;
    }

    public BaseException(ResponseCodes responseCodes, String message, Throwable cause) {
        super(message, cause);
        this.responseCodes = responseCodes;
    }

    public BaseException(ResponseCodes responseCodes, Throwable cause) {
        super(cause);
        this.responseCodes = responseCodes;
    }
}
