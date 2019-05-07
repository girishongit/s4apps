package com.cg.pbs.customerprofile.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HelloWorldResponse
{
    @JsonProperty("hello")
    private String name;

	public HelloWorldResponse(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
    
	public void setName(String name) {
		this.name = name;
	}
    
}
