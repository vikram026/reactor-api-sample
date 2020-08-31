package com.nisum.webflux.controller;

import com.nisum.webflux.config.KafkaServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
//basic kafka
public class SampleController {
	@Autowired
    private KafkaServer kafkaServer;
	
	@GetMapping("/send")
	public String producer(@RequestParam
                                   String msg) {
		log.info("into Controller");
		return kafkaServer.send(msg);		
	}
	
	

}
