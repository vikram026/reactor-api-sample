package com.nisum.webflux.service.impl;

import com.nisum.webflux.model.Fruit;
import com.nisum.webflux.repository.FruitRepository;
import com.nisum.webflux.service.IFruitService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FruitService implements IFruitService {

	@Autowired
	private FruitRepository fruitRepository;

	@Override
	public Mono<Fruit> save(Fruit fruit) {
		
		return fruitRepository.save(fruit);
	}
	public Mono<Fruit> get(Fruit fruit) {

		return fruitRepository.findAll().collect(Collectors.maxBy(Comparator.comparing(e->Integer.parseInt(e.getPrice())))).map(Optional::get);
	}
	


	@Override
	public Flux<Fruit> getAll() {
		return fruitRepository.findAll();
	}
	@Override
	public Mono<Fruit> getFruitById(String id) {
		return Optional.ofNullable(id).map(fruitRepository::findById).get();
	}
	@Override
	public Mono<Fruit> getFruitByPriceAndName(String price, String name) {
		return Optional.ofNullable(name).map(e->fruitRepository.findByNameAndPrice(e,price)).get();
	}
}
