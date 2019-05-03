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
@ControllerAdvice
@ResponseBody
public class HandleException  {

    @ExceptionHandler(value = MyException.class)
    public MyException handleException(MyException exception){
        MyException myException=new MyException();
        myException.setCode(exception.getCode());
        myException.setMessage(exception.getMessage());
        return myException;
    }
}
