package com.roxi.oauth.excpetion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Roxi酱
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyException extends RuntimeException{
    private int code;
    private String message;
}
