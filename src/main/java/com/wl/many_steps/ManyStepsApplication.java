package com.wl.many_steps;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wl.many_steps.mapper")
public class ManyStepsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManyStepsApplication.class, args);
    }

}
