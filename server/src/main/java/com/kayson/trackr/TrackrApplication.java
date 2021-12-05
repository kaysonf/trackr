package com.kayson.trackr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
public class TrackrApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrackrApplication.class, args);
	}

}
