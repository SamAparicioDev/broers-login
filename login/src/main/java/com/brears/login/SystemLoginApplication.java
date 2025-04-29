package com.brears.login;

import com.brears.login.models.UserDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@OpenAPIDefinition
public class SystemLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SystemLoginApplication.class, args);
	}
}
