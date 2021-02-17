package cn.com.testol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

@SpringBootApplication
public class TestolApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestolApplication.class, args);
    }
}
