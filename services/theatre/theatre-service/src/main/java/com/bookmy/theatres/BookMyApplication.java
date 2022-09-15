package com.bookmy.theatres;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//@EnableJpaRepositories
@EnableFeignClients(basePackages = {"com.bookmy"})
@EnableAutoConfiguration(exclude = ErrorMvcAutoConfiguration.class)
//https://github.com/zalando/problem-spring-web/tree/main/problem-spring-web#configuration
public class BookMyApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(BookMyApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }
}
