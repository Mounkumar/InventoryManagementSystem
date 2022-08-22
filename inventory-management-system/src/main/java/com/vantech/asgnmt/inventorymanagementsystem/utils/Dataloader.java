package com.vantech.asgnmt.inventorymanagementsystem.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import com.vantech.asgnmt.inventorymanagementsystem.model.Inventory;
import com.vantech.asgnmt.inventorymanagementsystem.repository.InventoryRepository;

@Component
public class Dataloader implements CommandLineRunner{
	
	@Autowired
	InventoryRepository repository;
	
	/*
	 * @PostConstruct public void loadDataToDB(String fileName) { List<Inventory>
	 * inventoryList = new ArrayList<Inventory>(); String path = fileName;
	 * 
	 * FileInputStream excelFile; try { File file =
	 * ResourceUtils.getFile("classpath:"+path); excelFile = new
	 * FileInputStream(file); XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
	 * XSSFSheet worksheet = workbook.getSheetAt(0);
	 * 
	 * for(int i=1; i< worksheet.getPhysicalNumberOfRows(); i++) { Inventory
	 * inventory = new Inventory();
	 * 
	 * XSSFRow row = worksheet.getRow(i);
	 * 
	 * inventory.setPartNumber(row.getCell(0).toString());
	 * inventory.setSerialNumber(row.getCell(1).toString());
	 * inventory.setInventoryQty(Double.valueOf(row.getCell(2).toString()));
	 * inventory.setAvailableQty(Double.parseDouble(row.getCell(3).toString()));
	 * inventory.setAllocatedQty(Double.valueOf(row.getCell(4).toString()));
	 * 
	 * inventoryList.add(inventory);
	 * 
	 * repository.save(inventory); } for ( Inventory inv : inventoryList) {
	 * 
	 * System.out.println(inv.getPartNumber()+" " +
	 * inv.getSerialNumber()+" "+inv.getAllocatedQty()); }
	 * System.out.println(inventoryList);
	 * 
	 * workbook.close();
	 * 
	 * } catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException
	 * e) { e.printStackTrace(); }
	 * 
	 * 
	 * }
	 */
	
	@Override
	public void run(String... args) throws Exception {

		List<Inventory> inventoryList = new ArrayList<Inventory>();
		String path = "Inventory_Sample.xlsx";
		
		FileInputStream excelFile;
		try {
			File file = ResourceUtils.getFile("classpath:"+path);
			excelFile = new FileInputStream(file);
			XSSFWorkbook workbook = new XSSFWorkbook(excelFile);
			XSSFSheet worksheet = workbook.getSheetAt(0);
			
			for(int i=1; i< worksheet.getPhysicalNumberOfRows(); i++) {
				Inventory inventory = new Inventory();
				
				XSSFRow row = worksheet.getRow(i);
				
				inventory.setPartNumber(row.getCell(0).toString());
				inventory.setSerialNumber(row.getCell(1).toString());
				inventory.setInventoryQty(Double.valueOf(row.getCell(2).toString()));
				inventory.setAvailableQty(Double.parseDouble(row.getCell(3).toString()));
				inventory.setAllocatedQty(Double.valueOf(row.getCell(4).toString()));
				
				inventoryList.add(inventory);
				
				repository.save(inventory);
			}
			for ( Inventory inv : inventoryList) {
				
				System.out.println(inv.getPartNumber()+" " + inv.getSerialNumber()+" "+inv.getAllocatedQty());
			}
			System.out.println(inventoryList);
			
			workbook.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
