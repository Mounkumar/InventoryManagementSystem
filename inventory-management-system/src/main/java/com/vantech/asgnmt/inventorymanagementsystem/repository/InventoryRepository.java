package com.vantech.asgnmt.inventorymanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vantech.asgnmt.inventorymanagementsystem.model.CompositeKey;
import com.vantech.asgnmt.inventorymanagementsystem.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, CompositeKey>{

}
