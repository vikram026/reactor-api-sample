package com.nisum.webflux.controller;

import com.nisum.webflux.model.Address;
import com.nisum.webflux.model.City;
import com.nisum.webflux.model.Employee;
import com.nisum.webflux.model.Hobby;
import com.nisum.webflux.service.IEmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
public class EmployeeControllerIntegrationTest {
	
	
	@Autowired
    private WebTestClient webTestClient;

	@InjectMocks
    private EmployeeController employeeController;

	@Mock
	private IEmployeeService employeeService;
	
	@Before
	public void setup() {
		
	}
	@Test
    public void save_UsingWebTestClient() {
		
		webTestClient.post().uri("/employee").contentType(MediaType.APPLICATION_JSON).body(Mono.just(getMockEmployee().blockFirst()), Employee.class)
    	.exchange()
    	.expectStatus().isOk().expectBody().jsonPath("$.id").isEqualTo("1");
    	}
	@Test
	public void getAll_UsingWebClientComponentTestCase() {
		webTestClient.get().uri("/employee").exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
		.expectBodyList(Employee.class)
		.hasSize(1);
	}
	
	@Test
	public void getAll_UsingWebClientUsingStepVerifier() {
		Flux<Employee> employeeFlux=webTestClient.get().uri("/employee").exchange()
		.expectStatus().isOk()
		.expectHeader().contentType(MediaType.APPLICATION_JSON)
	    .returnResult(Employee.class)
	    .getResponseBody();
		
		StepVerifier.create(employeeFlux.log()).expectSubscription()
		.expectNextCount(1).verifyComplete();
	}

	@Test
	public void getAll_UsingMockito_JunitTestCase(){
		Flux<Employee> employeeFlux=getMockEmployee();

		when(employeeService.getAllEmployee()).thenReturn(employeeFlux);
		assertEquals(employeeController.getAll(),employeeFlux);

	}
	private Flux<Employee> getMockEmployee() {
		List<Hobby> hobbyObj=new ArrayList<>();
		hobbyObj.add(new Hobby("1","cricket"));
		List<City> cities=Arrays.asList(new City("1","patna"));
		List<Address> address =Arrays.asList(new Address("1","bhr",cities));
		return Flux.just(new Employee("1","abc",address,hobbyObj));
	}
	


	
	
}
