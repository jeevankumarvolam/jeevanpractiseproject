package com.example.demo.entity;

import com.example.demo.entity.request.ProductRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@NoArgsConstructor

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Department {
private String name;
	public Department(ProductRequest d)
	{	this.name = d.getName();}

}
