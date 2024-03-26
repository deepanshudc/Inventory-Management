package com.project.Inventory.Management.App.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
public class ItemCategory {
	
	@Id
	@NotBlank(message="is required")
	@Pattern(regexp="^[a-zA-Z]*$", message="only aplhabet are allowed in Category Name")
	private String categoryName;
	
    @OneToMany(mappedBy = "categoryName")
	private List<Item> item;

	public ItemCategory(String categoryName, Item item) {
		super();
		this.categoryName = categoryName;
		this.item = (List<Item>) item;
	}

	public ItemCategory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public void setItem(Item item) {
		this.item = (List<Item>) item;
	}
	 
//	@Override
//	public String toString() {
//		// TODO Auto-generated method stub
//		return categoryName;
//	}
	

    
}
