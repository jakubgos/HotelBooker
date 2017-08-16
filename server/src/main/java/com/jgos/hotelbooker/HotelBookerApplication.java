package com.jgos.hotelbooker;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;

@SpringBootApplication
public class HotelBookerApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelBookerApplication.class, args);
    }

}
