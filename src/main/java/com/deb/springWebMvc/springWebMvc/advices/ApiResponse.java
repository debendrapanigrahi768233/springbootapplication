package com.deb.springWebMvc.springWebMvc.advices;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse<T> {
    private T data;
    private ApiError error;
    private LocalDateTime timestamp;


    public ApiResponse() {
        this.timestamp = LocalDateTime.now();
    }
    public ApiResponse(T data) {
        this();   //call the default constructor
        this.data = data;
    }

    public ApiResponse(ApiError error) {
        this();   //call the default constructorf
        this.error = error;
    }

}

