package com.project.Inventory.Management.App.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Inventory.Management.App.entity.Item;
import com.project.Inventory.Management.App.entity.ItemCategory;
import com.project.Inventory.Management.App.repository.ItemCategoryRepository;
import com.project.Inventory.Management.App.repository.ItemRepository;

import jakarta.transaction.Transactional;


@Service
public class ItemService {
	
	@Autowired
	public ItemRepository repository;
	
	@Autowired
	public ItemCategoryRepository categoryRepository;
	
	public List<Item> getAllItemList() {
		List<Item> list= new ArrayList<Item>();
		list=repository.findAll();
		return list;
	}
	
	public List<ItemCategory> getItemCategoryList(){
		List<ItemCategory> categoryList = new ArrayList<ItemCategory>();
		categoryList = categoryRepository.findAll();
		return categoryList;
	}
	
	@Transactional
	public void addItem(Item itemToAdd) {
		
			 repository.save(itemToAdd);
			}
	
	public Item getItem(String itemSKU) {
		Optional<Item> find=repository.findBySKU(itemSKU);
		Item found=null;
		if(find.isPresent()) {
			System.out.println("Item found");
			found= find.get();
			return found;
		}
		else {
			System.out.println("Item not found ..server error");
			return null;
		}
	}
	
	@Transactional
	public void updateItem(Item item) {
		String checkSKU=item.getSKU();
		System.out.println(checkSKU);
		Optional<Item> checkOptional=repository.findBySKU(checkSKU);
		if(!checkOptional.isPresent()) {
			throw new IllegalArgumentException("Invalid item SKU:" + checkSKU);
		}
		Item updateItem=  checkOptional.get();
		updateItem.updateItem(item);
		repository.save(updateItem);
		System.out.println("Item updated");
	}

	@Transactional
	public void deleteBySKU(String sKU) {
		
		repository.deleteBySKU(sKU);
		System.out.println("Item deleted");
		
	}

	
	
	 
	

}
