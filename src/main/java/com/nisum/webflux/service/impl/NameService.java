package com.nisum.webflux.service.impl;

import com.nisum.webflux.model.Fruit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class NameService {

	private String preName;
	public Mono<Fruit> name(Fruit fruit){
		
		return Mono.just(fruit).map(e->{
			this.preName=e.getName(); e.setName(preName+"+pro"); 
			return e;
		}).onErrorResume(e->{
			log.error("Error occurred during changing name"+e);
			return Mono.just(new Fruit("0","",""));
		});
	}
	public Mono<Fruit> revertName(Fruit fruit){
		return Mono.just(fruit).map(e->{
			e.setName(preName);
			return e;	
		}).onErrorResume(e->{
			log.error("Error occurred during changing name"+e);
			return Mono.just(new Fruit("0","",""));
		});
	}

}
