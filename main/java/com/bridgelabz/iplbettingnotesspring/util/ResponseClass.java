package com.bridgelabz.iplbettingnotesspring.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseClass {
    private int errorCode;
    private String message;
    private  Object token;

    public ResponseClass() {
    }
}
