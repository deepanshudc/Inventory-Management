package com.project.Inventory.Management.App.entity;


import com.project.Inventory.Management.App.model.ItemModel;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Item {
	@Id
	private String SKU;
	private String itemName;
	private  int itemQuantity;
	private double costPrice;
	private double sellPrice;
	private String itemBrand;

	@ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.PERSIST})
	@JoinColumn(name="itemCategory", referencedColumnName = "categoryName")
	private ItemCategory categoryName;

	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	    

	public Item(String sKU, String itemName, int itemQuantity, double costPrice, double sellPrice, String itemBrand,
			ItemCategory categoryName) {
		super();
		this.SKU = sKU;
		this.itemName = itemName;
		this.itemQuantity = itemQuantity;
		this.costPrice = costPrice;
		this.sellPrice = sellPrice;
		this.itemBrand = itemBrand;
		this.categoryName = categoryName;
	}

	public Item(ItemModel item) {
		// TODO Auto-generated constructor stub
	    this.categoryName = new ItemCategory();

		this.SKU = item.getSKU();
		this.itemName = item.getItemName();
		this.itemQuantity = item.getItemQuantity();
		this.costPrice = item.getCostPrice();
		this.sellPrice = item.getSellPrice();
		this.itemBrand = item.getItemBrand();
		this.categoryName.setCategoryName(item.getItemCategory());
	}

	public String getSKU() {
		return SKU;
	}

	public void setSKU(String sKU) {
		SKU = sKU;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public int getItemQuantity() {
		return itemQuantity;
	}

	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public double getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(double costPrice) {
		this.costPrice = costPrice;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getItemBrand() {
		return itemBrand;
	}

	public void setItemBrand(String itemBrand) {
		this.itemBrand = itemBrand;
	}

	public ItemCategory getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(ItemCategory categoryName) {
		this.categoryName = categoryName;
	}

	public void updateItem(ItemModel item) {
		
	    this.categoryName = new ItemCategory();

		// TODO Auto-generated method stub
		this.itemName = item.getItemName();
		this.itemQuantity = item.getItemQuantity();
		this.costPrice = item.getCostPrice();
		this.sellPrice = item.getSellPrice();
		this.itemBrand = item.getItemBrand();
		this.categoryName.setCategoryName(item.getItemCategory());
		
	}
	



	public void updateItem(Item item) {
		// TODO Auto-generated method stub
	//	this.categoryName = new ItemCategory();

		// TODO Auto-generated method stub
		this.itemName = item.getItemName();
		this.itemQuantity = item.getItemQuantity();
		this.costPrice = item.getCostPrice();
		this.sellPrice = item.getSellPrice();
		this.itemBrand = item.getItemBrand();
		this.categoryName=item.categoryName;
		
		
		
	}
	
	
	
	

	
	

}
