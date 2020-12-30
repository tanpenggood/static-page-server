package com.itplh.page;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class StaticPageApplication {

    public static void main(String[] args) {
        Arrays.asList(args).stream().forEach(System.out::println);
        SpringApplication.run(StaticPageApplication.class, args);
    }

}
