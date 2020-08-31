package com.nisum.webflux.repository;

import com.nisum.webflux.model.Fruit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface FruitRepository extends ReactiveMongoRepository<Fruit, String> {

	Mono<Fruit> findByNameAndPrice(String e, String price);
	

}
