package com.example.spring_securities_1;

import com.example.spring_securities_1.entities.User;
import com.example.spring_securities_1.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class SpringSecurities1ApplicationTests {

	@Autowired
	private JwtService jwtService;

	@Test
	void contextLoads() {
		User user = new User(5L,"venumadhav@gmail.com" ,"venu" ,"venu123" );

		String token = jwtService.generateToken(user);

		System.out.println(token);

		Long id = jwtService.getUserIdFromToken(token);

		Assert.isTrue(id==5L,"id must should be equal");
	}

}
