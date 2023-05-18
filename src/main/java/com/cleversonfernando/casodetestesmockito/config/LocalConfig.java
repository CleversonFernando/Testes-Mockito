package com.cleversonfernando.casodetestesmockito.config;

import com.cleversonfernando.casodetestesmockito.domain.User;
import com.cleversonfernando.casodetestesmockito.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class LocalConfig {

    @Autowired
    private UserRepository userRepository;

    @Bean
    public void startDB() {
        User u1 = new User(null, "Valdir", "valdir@gmail.com", "12345");
        User u2 = new User(null, "Pedro", "pedro@gmail.com", "54321");

        userRepository.saveAll(Arrays.asList(u1, u2));
    }

}
