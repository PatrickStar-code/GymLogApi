package com.Gymlog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
public class GymlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(GymlogApplication.class, args);
	}

}
