package com.bridgelabz.iplbettingnotesspring.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
@Data
@AllArgsConstructor
public class UserNotFound extends RuntimeException {
    private Integer statusCode;
    private String statusMessage;
}
