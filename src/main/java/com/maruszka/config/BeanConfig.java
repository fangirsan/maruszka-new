package com.maruszka.config;

import com.maruszka.utils.DuplicateCheck;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

public class BeanConfig {

    @Bean
    DuplicateCheck duplicateCheck() {
        return new DuplicateCheck();
    }

}
