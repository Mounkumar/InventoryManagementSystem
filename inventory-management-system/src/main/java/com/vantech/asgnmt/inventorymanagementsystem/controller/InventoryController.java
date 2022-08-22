package com.vantech.asgnmt.inventorymanagementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vantech.asgnmt.inventorymanagementsystem.service.InventoryService;
import com.vantech.asgnmt.inventorymanagementsystem.utils.Response;

@RestController
@RequestMapping("/inventory/v1")
public class InventoryController {
	
	@Autowired
	InventoryService invService;
	
	@PutMapping("/SA-IN")
	public ResponseEntity<Response>  addQuantity(@Param(value = "partNumber") String partNumber, 
			@Param(value = "serialNumber") String serialNumber, @Param(value = "quantity") Double qty) {
		Response response = invService.addQuantity(partNumber, serialNumber, qty);
		
	    return new ResponseEntity<Response>(response, response.status);
		
		
		
	}
	@PutMapping("/SA-OUT")
	public ResponseEntity<Response> deleteQuantity(@Param(value = "partNumber") String partNumber, 
			@Param(value = "serialNumber") String serialNumber, @Param(value = "quantity") Double qty) {
		Response response = invService.deductQuantity(partNumber, serialNumber, qty);
		
	    return new ResponseEntity<Response>(response, response.status);
	}

}
