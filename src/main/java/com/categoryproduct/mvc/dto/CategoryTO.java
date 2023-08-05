package com.categoryproduct.mvc.dto;

import java.util.ArrayList;
import java.util.List;

import com.categoryproduct.mvc.model.Product;

public class CategoryTO {

	Integer categoryId;
	
	String categoryName;
	
	List<ProductTO> productList=new ArrayList<ProductTO>();

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	
	public List<ProductTO> getProductList() {
		return productList;
	}

	public void setProductList(List<ProductTO> productList) {
		this.productList = productList;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
