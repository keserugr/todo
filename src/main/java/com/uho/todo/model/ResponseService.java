package com.uho.todo.model;

import com.uho.todo.enums.ResponseCodes;
import lombok.Data;

@Data
public class ResponseService<T> {

    private T data;
    private int status;
    private String message;

    public ResponseService(){
        this.status = ResponseCodes.SUCCESS.getHttpStatus().value();
    }


    public ResponseService(T data) {
        this.data = data;
        this.status = ResponseCodes.SUCCESS.getHttpStatus().value();
    }

    public ResponseService(int status,String message){
        this.status = status;
        this.message = message;
        this.data = null;
    }



}
