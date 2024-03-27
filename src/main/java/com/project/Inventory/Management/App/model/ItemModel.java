package com.project.Inventory.Management.App.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ItemModel {
	
	public ItemModel() {
		super();
	}

	 	@NotBlank(message="is required")
	 	@Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Only alphanumeric characters are allowed")
		private String SKU ;
		
	 	@NotBlank(message="is required")
		private String itemName;
		
	 	@NotNull(message="is required")
		private int itemQuantity;
		
	 	@NotNull(message="is required")
		private double costPrice;
		
	 	@NotNull(message="is required")
		private double sellPrice;
		
		@NotBlank(message="is required")
		private String itemBrand;
		
		@NotBlank(message="is required")
		private String itemCategory;

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

		public String getItemCategory() {
			return itemCategory;
		}

		public void setItemCategory(String itemCategory) {
			this.itemCategory = itemCategory;
		}

		@Override
		public String toString() {
			return "ItemModel [SKU=" + SKU + ", itemName=" + itemName + ", itemQuantity=" + itemQuantity
					+ ", costPrice=" + costPrice + ", sellPrice=" + sellPrice + ", itemBrand=" + itemBrand
					+ ", itemCategory=" + itemCategory + "]";
		}
		
		
		
		
		
}
