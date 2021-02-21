package com.uho.todo.exception;

import com.uho.todo.enums.ResponseCodes;
import com.uho.todo.model.ResponseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Object> handleBaseException(BaseException ex) {
        log.debug("BaseException ->", ex);
        return new ResponseEntity<>(new ResponseService<Object>(ex.getResponseCodes().getHttpStatus().value(), createMessage(ex, ex.getResponseCodes())), ex.getResponseCodes().getHttpStatus());
    }

    private String createMessage(BaseException exception, ResponseCodes responseCodes) {
        if (StringUtils.isEmpty(exception.getMessage())) {
            return responseCodes.getMessage();
        }
        return exception.getMessage();
    }


}
