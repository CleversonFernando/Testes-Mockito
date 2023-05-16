package com.cleversonfernando.casodetestesmockito;

import com.cleversonfernando.casodetestesmockito.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CasodetestesmockitoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CasodetestesmockitoApplication.class, args);
    }

    User user = new User(1, "Valdir", "valdir@gmail.com", "12345");
}
