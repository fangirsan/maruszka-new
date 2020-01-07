package com.maruszka;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
public class MaruszkaApplication {

    public static void main(String[] args) {
        run(MaruszkaApplication.class, args);
    }

}
