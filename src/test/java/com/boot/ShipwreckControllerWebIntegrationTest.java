package com.boot;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;
import org.junit.*;
import com.boot.repository.ShipwreckRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import java.util.*;
import com.boot.model.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
@WebIntegrationTest
public class ShipwreckControllerWebIntegrationTest {

	@Mock
	private ShipwreckRepository shipwreckRepository;
	
	@Test
	public void testFindAll() {
		RestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/v1/shipwrecks", String.class);
		assertThat(response.getStatus(), equalTo(HttpStatus.OK));
		
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode responseJson = objectMapper.readTree(response.getBody());
		
		assertThat(responseJson.isMissingNode(), is(false));
		assertThat(responseJson.toString(), equalTo("[]"));
	}
}
