package com.example.spring_securities_1;

import com.example.spring_securities_1.dto.LoginResponseDto;
import com.example.spring_securities_1.entities.User;
import com.example.spring_securities_1.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static com.example.spring_securities_1.entities.enums.Role.ADMIN;

@SpringBootTest
class SpringSecurities1ApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
//		User user = new User(5L,"venumadhav@gmail.com" ,"venu" ,{ADMIN.name()},"venu123" );
//
//		String token = jwtService.generateAccessToken(user);
//
//		System.out.println(token);
//
//		Long id = jwtService.getUserIdFromToken(token);
//
//		Assert.isTrue(id==5L,"id must should be equal");
	}

}
