package com.nisum.webflux.controller;

import com.nisum.webflux.model.Fruit;
import com.nisum.webflux.repository.FruitRepository;
import com.nisum.webflux.service.impl.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@ContextConfiguration(classes = TestConfig.class)
public class FruitControllerComponentTest {
    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    private FruitRepository fruitRepo;

    @Test
    public void save() {
        ArgumentCaptor<Fruit> requestCaptor = ArgumentCaptor.forClass(
                Fruit.class);
        when(fruitRepo.save(requestCaptor.capture())).thenReturn(Mono.error(new RuntimeException()));


        Fruit fruit=new Fruit("1","apple","300");
        webTestClient.post().uri("/fruit").contentType(MediaType.APPLICATION_JSON_UTF8).body(BodyInserters.fromObject(fruit))
                .exchange()
                .expectStatus().isOk().expectBody().jsonPath("$.id").isEqualTo("1");
    }
}
