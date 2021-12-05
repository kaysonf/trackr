package com.kayson.trackr.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
//            User kayson = new User("kayson_handle", "kayson_email");
//
//            User sonkay = new User("sonkay_handle", "sonkay_email");
//
//            repository.saveAll(
//                    List.of(kayson, sonkay)
//            );
        };

    }

}
