package com.example.foodcart;

public class FoodItem {
    
    private String itemName;
    private boolean isChecked = false;
     
    public FoodItem(String itemName, boolean isChecked){
        this.itemName = itemName;
        this.isChecked = isChecked;
	}
    
	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
}