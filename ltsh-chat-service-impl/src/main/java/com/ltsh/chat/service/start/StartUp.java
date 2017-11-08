package com.ltsh.chat.service.start;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by Random on 2017/06/21.
 */


@ImportResource("classpath:/spring/spring-context.xml")
@SpringBootApplication(excludeName = "com.ltsh.service.impl")
public class StartUp {
    public static void main(String[] args) {
        SpringApplication.run(StartUp.class, args);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(60000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}