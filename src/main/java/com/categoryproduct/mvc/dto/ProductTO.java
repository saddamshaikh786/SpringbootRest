package com.categoryproduct.mvc.dto;

import com.categoryproduct.mvc.model.Category;

public class ProductTO {

	Integer productId;
	
	String productName;
	
	
	Integer CategoryId;


	public Integer getProductId() {
		return productId;
	}


	public void setProductId(Integer productId) {
		this.productId = productId;
	}


	


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public Integer getCategoryId() {
		return CategoryId;
	}


	public void setCategoryId(Integer categoryId) {
		CategoryId = categoryId;
	}


	

	
	
	
}
