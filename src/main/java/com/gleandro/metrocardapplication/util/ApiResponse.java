package com.gleandro.metrocardapplication.util;

import lombok.Data;

@Data
public class ApiResponse<T> {

    private Boolean success;
    private T data;
    private String code;
    private String message;

}
