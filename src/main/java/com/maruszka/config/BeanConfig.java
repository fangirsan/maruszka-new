package com.maruszka.config;

import com.maruszka.utils.DuplicateCheck;
import org.springframework.context.annotation.Bean;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

public class BeanConfig {

    @Bean
    DuplicateCheck duplicateCheck() {
        return new DuplicateCheck();
    }

    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

}
