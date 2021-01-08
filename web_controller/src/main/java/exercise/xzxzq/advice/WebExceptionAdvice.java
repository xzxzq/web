package exercise.xzxzq.advice;

import exercise.xzxzq.constant.ErrorLogConstant;
import exercise.xzxzq.entity.Result;
import exercise.xzxzq.exception.BusinessException;
import exercise.xzxzq.exception.UnsupportedArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Author : 徐志清
 * @Description : 异常处理
 * @Time : 2021/1/6 19:37
 * @File : WebExceptionAdvice
 */
@RestControllerAdvice
public class WebExceptionAdvice {
    public static final Logger log = LoggerFactory.getLogger(WebExceptionAdvice.class);

    @ExceptionHandler(BusinessException.class)
    public Result handleException(BusinessException e){
        return new Result(false,e.getMessage());
    }

    @ExceptionHandler(UnsupportedArgumentException.class)
    public Result handleException(UnsupportedArgumentException e){
        return new Result(false,e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e){
        log.error(e.getMessage());
        return new Result(false, ErrorLogConstant.UNKNOWN_ERROR);
    }
}
