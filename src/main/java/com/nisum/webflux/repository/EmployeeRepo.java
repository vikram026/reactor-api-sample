package com.nisum.webflux.repository;

import com.nisum.webflux.model.Employee;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface EmployeeRepo  extends ReactiveMongoRepository<Employee, String> {
	Flux<Employee> findByName(String name);
	
	@Query("{'address.city.cityName': ?0}")
    Flux<Employee> findByCity(String city);
	@Query("{'hobbies.hobbyName': ?0}")
    Flux<Employee> findbyHobbyName(String hobbyName);
	@Query("{'name': ?0 ,'hobbys.hobbyName': ?1}")
    Flux<Employee> findByNameAndHobbyName(String name, String hobbyName);
	@Query("{'name': ?0 , 'address.city.cityName': ?1 , 'hobbies.hobbyName': ?2}")
    Flux<Employee> findByNameAndCityAndHobby(String name, String cityName, String hobbyName);

}
