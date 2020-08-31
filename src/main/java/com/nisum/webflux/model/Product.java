package com.nisum.webflux.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Data
@NoArgsConstructor
public class Product {
	
	private  String id;
	private  String name;
	private  String price;
	

}
