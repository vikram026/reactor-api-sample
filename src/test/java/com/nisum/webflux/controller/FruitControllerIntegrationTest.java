package com.nisum.webflux.controller;


import com.nisum.webflux.model.Fruit;
import com.nisum.webflux.repository.FruitRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
public class FruitControllerIntegrationTest {
	
	@Autowired
    private WebTestClient webTestClient;

	@Test
	public void save() {
			Fruit fruit= new Fruit("1","apple","300");
		webTestClient.post().uri("/fruit").contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(fruit))
    	.exchange()
    	.expectStatus().isOk().expectBody().jsonPath("$.id").isEqualTo("1");
    	}

}
