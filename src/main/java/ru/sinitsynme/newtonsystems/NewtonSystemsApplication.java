package ru.sinitsynme.newtonsystems;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class NewtonSystemsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewtonSystemsApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {

        };
    }

}
