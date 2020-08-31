package com.nisum.webflux.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Employee {
	@Id
	private String id;
	private String name;
	private List<Address> address;	
	private  List<Hobby> hobbies;

}
