package com.nisum.webflux.controller;

import com.nisum.webflux.model.Employee;
import com.nisum.webflux.service.IEmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class EmployeeController {
	
	@Autowired
	private IEmployeeService employeeService;


	@PostMapping("/employee")
	public Mono<Employee> save(@RequestBody
                                       Employee employee) {
		log.info("employee obj "+employee);
		return employeeService.save(employee);
	}
	
	
	@GetMapping("/employee")
	public Flux<Employee> getAll(){
		return employeeService.getAllEmployee();
	}
	@GetMapping("/employee/byName")
	public Flux<Employee> findByName(@RequestParam
                                             String name){
		
		return employeeService.findByName(name);
	}
	
	@GetMapping("/employee/byCity")
	public Flux<Employee> findByCity(@RequestParam
                                             String city){

		return employeeService.findByCity(city);
	}
	@GetMapping("/employee/byHobby")
	public Flux<Employee> findByHobbyName(@RequestParam
                                                  String hobbyName){

		return employeeService.findByHobbyName(hobbyName);
}

	@GetMapping("/employee/byNameAndHobby")
	public Flux<Employee> findByNameAndHobbyName(@RequestParam
														 String name, @RequestParam
														 String hobbyName){
		return employeeService.findByNameAndHobbyName(name,hobbyName);
	}
	@GetMapping("/employee/byNameAndCityAndHobby")
	public Flux<Employee> findByNameAndCityAndHobby(@RequestParam
															String name, @RequestParam
															String cityName, @RequestParam
															String hobbyName){

		return employeeService.findByNameAndCityAndHobby(name,cityName,hobbyName);
	}
}
