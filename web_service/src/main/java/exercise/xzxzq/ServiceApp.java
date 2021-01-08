package exercise.xzxzq;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @Author 徐志清
 * @Description //TODO 
 * @Date 9:33 2021/1/6
 * @Param 
 * @return 
 **/
public class ServiceApp {
    public static void main(String[] args) throws IOException {

        new ClassPathXmlApplicationContext("classpath:spring-service.xml");
        System.in.read();
    }
}
