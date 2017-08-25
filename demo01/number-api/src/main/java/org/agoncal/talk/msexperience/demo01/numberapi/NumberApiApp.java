package org.agoncal.talk.msexperience.demo01.numberapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class NumberApiApp {

    public static void main(String[] args) {
        SpringApplication.run(NumberApiApp.class, args);
    }
}
