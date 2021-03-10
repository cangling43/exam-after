package cn.com.testol;

//import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

//@EnableSwagger2Doc
@SpringBootApplication
public class TestolApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestolApplication.class, args);
    }
}
