package com.vantech.asgnmt.inventorymanagementsystem.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@IdClass(CompositeKey.class)
public class Inventory {
	
	@Id
	private String partNumber;
	@Id
	private String serialNumber;
	private Double inventoryQty;
	private Double availableQty;
	private Double allocatedQty;
	
}
