package com.nisum.webflux.service.impl;

import com.nisum.webflux.model.Address;
import com.nisum.webflux.model.City;
import com.nisum.webflux.model.Employee;
import com.nisum.webflux.model.Hobby;
import com.nisum.webflux.repository.EmployeeRepo;
import com.nisum.webflux.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class EmployeeService implements IEmployeeService{
	@Autowired
	private EmployeeRepo empRepo;

	@Override
	public Mono<Employee> save(Employee employee) {
		log.info("Into save method");
		return empRepo.save(employee).onErrorResume(e->{
			log.info("into Error "+e);
			return getMockEmployee();
		});
	}
	private Mono<Employee> getMockEmployee() {
		List<Hobby> hobbyObj=new ArrayList<>();
		hobbyObj.add(new Hobby("1","cricket"));
		List<City> cities=Arrays.asList(new City("1","patna"));
		List<Address> address =Arrays.asList(new Address("1","bhr",cities));
		return Mono.just(new Employee("2","servicename",address,hobbyObj));
		
	}

	@Override
	public Flux<Employee> getAllEmployee() {
		
		return empRepo.findAll().onErrorResume(e->{
			log.info("into error for getAllEmployee");
			return Flux.just(getMockEmployee().block());});
	}

	@Override
	public Flux<Employee> findByName(String name) {

		return empRepo.findByName(name);
	}

	@Override
	public Flux<Employee> findByCity(String city) {

		return empRepo.findByCity(city);
	}

	@Override
	public Flux<Employee> findByHobbyName(String hobbyName) {

		return empRepo.findbyHobbyName(hobbyName);
	}

	@Override
	public Flux<Employee> findByNameAndHobbyName(String name, String hobbyName) {
		return empRepo.findByNameAndHobbyName(name, hobbyName);
	}

	@Override
	public Flux<Employee> findByNameAndCityAndHobby(String name, String cityName, String hobbyName) {

		return empRepo.findByNameAndCityAndHobby(name,cityName,hobbyName);
	}

}
