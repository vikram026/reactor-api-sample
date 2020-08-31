package com.nisum.webflux.service.impl;

import com.nisum.webflux.repository.EmployeeRepo;
import com.nisum.webflux.repository.FruitRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;

@TestConfiguration
public class TestConfig {
	
	@MockBean
    private EmployeeRepo empRepo;
	@MockBean
    private FruitRepository fruitRepo;

}
