package com.vantech.asgnmt.inventorymanagementsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.vantech.asgnmt.inventorymanagementsystem.model.CompositeKey;
import com.vantech.asgnmt.inventorymanagementsystem.model.Inventory;
import com.vantech.asgnmt.inventorymanagementsystem.repository.InventoryRepository;
import com.vantech.asgnmt.inventorymanagementsystem.utils.Response;

@Service
public class InventoryService {
	
	@Autowired
	InventoryRepository repository;
	
	public List<String> errorlist;
	
	public Response addQuantity(String partNumber, String serialNumber, Double qty) {
		
		Response response;
		Inventory inventory = new Inventory();
		CompositeKey compositeID = new CompositeKey(partNumber, serialNumber);
		inventory = repository.findById(compositeID).get();
		
		validateInputParams(partNumber, serialNumber, qty);
		
		if(errorlist.isEmpty()) {
			
			inventory.setAvailableQty(inventory.getAvailableQty() + qty);
			inventory.setInventoryQty(inventory.getAllocatedQty()+inventory.getAvailableQty());
			Inventory savedInv =  repository.save(inventory);
			response = new Response(HttpStatus.ACCEPTED, "Updated Quantity Successfully", savedInv, errorlist);
			
		}
		else {
			response = new Response(HttpStatus.BAD_REQUEST, "Validation for input params failed", null, errorlist);
		}
		
		return response;
	}
	
	public Response deductQuantity(String partNumber, String serialNumber, Double qty) {
		
		Response response;
		Inventory inventory = new Inventory();
		CompositeKey compositeID = new CompositeKey(partNumber, serialNumber);
		inventory = repository.findById(compositeID).get();
		
		validateInputParams(partNumber, serialNumber, qty);
		
		if(errorlist.isEmpty()) {
			
			if(inventory.getAvailableQty().equals(qty)) {
				repository.delete(inventory);
				response = new Response(HttpStatus.OK, "Deleted the record as quantity became zero", null, errorlist);
			}
			else if(inventory.getAvailableQty() < qty){
				errorlist.add("available quanitty cannot be negative");
				response = new Response(HttpStatus.BAD_REQUEST, "Quantity cannot be greater than the available quantity", null, errorlist);
			}
			else {
				
				inventory.setAvailableQty(inventory.getAvailableQty() - qty);
				inventory.setInventoryQty(inventory.getAllocatedQty()-inventory.getAvailableQty());
				Inventory savedInv =  repository.save(inventory);
				response = new Response(HttpStatus.ACCEPTED, "Updared Quantity Successfully", savedInv, errorlist);
			}
		}
		else {
			response = new Response(HttpStatus.BAD_REQUEST, "Validation for input params failed", null, errorlist);
		}
		
		return response;
		
		
	}
	
	public void validateInputParams(String partNumber, String serialNumber, Double qty) {
		
		errorlist = new ArrayList<String>();
		
		if(partNumber == null || partNumber.isBlank()) {
			errorlist.add("Part number is missing or provided part number is not valid");
		}
		if(serialNumber == null || serialNumber.isBlank()) {
			errorlist.add("Serial Number is Not provided or Provided Serial Number is not valid");
		}
		if(qty == null || qty.isNaN()) {
			errorlist.add("Provided Quantity is not a valid value or the quantityt value is not provided");
		}
	}

}
