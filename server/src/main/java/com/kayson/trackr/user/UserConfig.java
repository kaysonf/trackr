package com.kayson.trackr.user;

import com.kayson.trackr.user.dto.CreateUserDTO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

//    @Bean
//    CommandLineRunner commandLineRunner(UserService userService) {
//        return args -> {
//            CreateUserDTO kayson = new CreateUserDTO();
//            kayson.setHandle("kayson_handle");
//            kayson.setEmail("kayson_email@a.co");
//            kayson.setPassword("12345678");
//            userService.createNewUser(kayson);
//
//            CreateUserDTO sonkay = new CreateUserDTO();
//            kayson.setHandle("sonkay_handle");
//            kayson.setEmail("sonkay_email@a.co");
//            kayson.setPassword("12345678");
//            userService.createNewUser(sonkay);
//        };
//    }

}
