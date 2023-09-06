package com.Musify;

import com.Musify.DataTables.Users;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class MusifyApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	@DirtiesContext
	void shouldCreateANewUser(){
		Users user= new Users(null, "Elon", "Musk", "none","nonr", "US", "user", false, false);
		ResponseEntity<Void> createResponse=restTemplate.postForEntity("/user/registration", user, Void.class);
		assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

//		URI locationOfNewCashCard = createResponse.getHeaders().getLocation();
//		ResponseEntity<String> getResponse = restTemplate
//				.getForEntity(locationOfNewCashCard, String.class);
//		assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

//		DocumentContext documentContext = JsonPath.parse(getResponse.getBody());
//		Number id = documentContext.read("$.id");
//		String country = documentContext.read("$.country");

//		assertThat(id).isNotNull();
//		assertThat(country).isEqualTo("US");
	}

}
