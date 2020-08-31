package com.nisum.webflux.repository;

import com.nisum.webflux.model.Hobby;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface HobbyRepo extends ReactiveMongoRepository<Hobby, String> {

}
