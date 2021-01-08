package exercise.xzxzq.exception;

/**
 * @Author : 徐志清
 * @Description :
 * @Time : 2021/1/8 19:44
 * @File : BusinessException
 */
public class BusinessException extends RuntimeException{
    public BusinessException(String message) {
        super(message);
    }
}
