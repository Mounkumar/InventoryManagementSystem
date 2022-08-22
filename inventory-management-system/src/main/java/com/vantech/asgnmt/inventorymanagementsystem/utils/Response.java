package com.vantech.asgnmt.inventorymanagementsystem.utils;

import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class Response {
	
	public HttpStatus status;
	public String message;
	public Object data;
	public List<String> errors;
	

}
