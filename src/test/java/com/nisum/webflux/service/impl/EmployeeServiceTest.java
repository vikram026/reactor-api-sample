package com.nisum.webflux.service.impl;


import com.nisum.webflux.model.Address;
import com.nisum.webflux.model.City;
import com.nisum.webflux.model.Employee;
import com.nisum.webflux.model.Hobby;
import com.nisum.webflux.repository.EmployeeRepo;
import com.nisum.webflux.service.impl.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EmployeeServiceTest {
	@Mock
    private EmployeeRepo empRepo;
	@InjectMocks
    private EmployeeService employeeService;

	@Test
	public void getAllEmployeeTestUsingStepVerifier() {
		Flux<Employee> empFlux=getFluxEmployee();
		when(empRepo.findAll()).thenReturn(empFlux);
		StepVerifier.create(employeeService.getAllEmployee()).expectSubscription()
		.expectNextMatches(e->e.equals(empFlux.blockFirst()))
		.verifyComplete();
	}

	@Test
	public void saveUsingStepVerifier() {
		Mono<Employee> empMono=getMonoEmployee();
		Employee e=new Employee();

		when(empRepo.save(e)).thenReturn(empMono);
		StepVerifier.create(employeeService.save(e))
		.expectNextCount(1)
				.verifyComplete();
	}
	@Test
	public void saveSVConsumeNextWith() {
		Mono<Employee> empMono=getMonoEmployee();
		Employee e=new Employee();

		when(empRepo.save(e)).thenReturn(empMono);
			StepVerifier.create(employeeService.save(e))
		.consumeNextWith(res->res.equals(empMono.blockOptional().get())).verifyComplete();
	}
	private Mono<Employee> getMonoEmployee() {
		List<Hobby> hobbyObj=new ArrayList<>();
		hobbyObj.add(new Hobby("1","cricket"));
		List<City> cities=Arrays.asList(new City("1","patna"));
		List<Address> address =Arrays.asList(new Address("1","bhr",cities));
		return Mono.just(new Employee("1","abc",address,hobbyObj));
	}
	
	private Flux<Employee> getFluxEmployee() {
		List<Hobby> hobbyObj=new ArrayList<>();
		hobbyObj.add(new Hobby("1","cricket"));
		List<City> cities=Arrays.asList(new City("1","patna"));
		List<Address> address =Arrays.asList(new Address("1","bhr",cities));

		return Flux.just(new Employee("1","abc",address,hobbyObj));
		
	}
	

}
