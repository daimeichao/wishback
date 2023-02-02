package com.jiading.config;



import com.jiading.common.exception.VtronLogicExceptionCode;
import com.jiading.common.util.ResultMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public ResultMap ExceptionHandler(Exception e) {
        e.printStackTrace();
        return ResultMap.error(VtronLogicExceptionCode.INTERNAL_SERVER_ERROR.getCode(), VtronLogicExceptionCode.INTERNAL_SERVER_ERROR.getMsg());
    }
}
