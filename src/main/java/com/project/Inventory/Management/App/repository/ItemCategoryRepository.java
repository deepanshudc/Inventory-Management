package com.project.Inventory.Management.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Inventory.Management.App.entity.ItemCategory;

public interface ItemCategoryRepository extends JpaRepository<ItemCategory,String> {
	
	

}
