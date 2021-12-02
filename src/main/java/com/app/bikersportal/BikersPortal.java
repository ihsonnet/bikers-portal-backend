package com.app.bikersportal;

import com.app.bikersportal.jwt.model.Role;
import com.app.bikersportal.jwt.model.RoleName;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BikersPortal {

	public static void main(String[] args) {
		SpringApplication.run(BikersPortal.class, args);
		Role user = new Role(1L, RoleName.USER);
		Role admin = new Role(2L, RoleName.ADMIN);
	}

}
