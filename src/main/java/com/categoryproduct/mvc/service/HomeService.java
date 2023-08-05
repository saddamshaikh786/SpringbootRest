package com.categoryproduct.mvc.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.categoryproduct.mvc.dao.CategoryDao;
import com.categoryproduct.mvc.dao.ProductDao;
import com.categoryproduct.mvc.dto.CategoryTO;
import com.categoryproduct.mvc.dto.ProductTO;
import com.categoryproduct.mvc.model.Category;
import com.categoryproduct.mvc.model.Product;

@Service
public class HomeService {

	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	ProductDao productDao;
	
	public List<CategoryTO> getAllCategories(){
		List<Category> categoryList= categoryDao.findAll();
		List<CategoryTO> categoryTOsList=new ArrayList<>();
		
		for(Category categoryDO :categoryList) {
			CategoryTO categoryTO=new CategoryTO();
			
			getCategoryToFromDo(categoryTO, categoryDO);
			categoryTOsList.add(categoryTO);
			
		}
		return categoryTOsList;
	}
	
	public CategoryTO getCategoryById(Integer categoryId) {
		CategoryTO categoryTO=new CategoryTO();
		try {
		Category categoryDO=categoryDao.getById(categoryId);
		getCategoryToFromDo(categoryTO, categoryDO);
		}catch(EntityNotFoundException e) {
			return categoryTO;
		}
		return categoryTO;
	}
	
	public boolean deleteCategoryById(Integer categoryId) {
		boolean flag=categoryDao.existsById(categoryId);
		if(flag) {
			categoryDao.deleteById(categoryId);
			return true;
		}
		
		return flag;
	}
	
	public boolean deleteProductById(Integer productId) {
		boolean flag=productDao.existsById(productId);
		if(flag) {
			productDao.deleteById(productId);
			return true;
		}
		return flag;
	}
	
	public ProductTO getProductById(Integer productId) {
		ProductTO productTO=new ProductTO();
		try {
		Product productDO=productDao.getById(productId);
		getProductToFromDO(productTO, productDO);
		}catch(EntityNotFoundException e) {
			return productTO;
		}
		
		return productTO;
	}
	
	public List<ProductTO> getAllProducts(){
		List<Product> productList=productDao.findAll();
		
		List<ProductTO> prodTOsList=new ArrayList<>();
		
		for(Product prodDO:productList) {
			ProductTO prodTO=new ProductTO();
			
			getProductToFromDO(prodTO,prodDO);
			
			prodTOsList.add(prodTO);
			
		}
		return prodTOsList;
	}
	
	
	public boolean saveCatogory(Map<String,Object> input) {
		Category category=new Category();
		
		try {
		
		String categoryName=input.get("categoryName").toString();
		
		category.setCategoryName(categoryName);
		
		List<Map<String,Object>> prodList=(List<Map<String,Object>>)input.get("productList");
		
		List<Product> prodDOsList=new ArrayList<>();
		for(Map<String,Object> prod:prodList) {
			String productName=prod.get("productName").toString();
			Product product=new Product();
			
			product.setProductName(productName);
			
			product.setCategory(category);
			prodDOsList.add(product);
		}
		category.setProdList(prodDOsList);
		categoryDao.save(category);
		}catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
	
	public boolean saveProduct(Map<String,Object> input) {
		Product product=new Product();
		
		try {
			String productName=input.get("productName").toString();
			Integer categoryId=Integer.parseInt(input.get("categoryId").toString());
			product.setProductName(productName);
			Category category=new Category();
			category.setCategoryId(categoryId);
			product.setCategory(category);
			
			productDao.save(product);
		}catch(Exception e) {
			return false;
		}
		
		return true;
	}
	
	public void getCategoryToFromDo(CategoryTO categoryTO,Category categoryDO) {
		categoryTO.setCategoryId(categoryDO.getCategoryId());
		
		categoryTO.setCategoryName(categoryDO.getCategoryName());
		
		List<Product> prodDOsList=categoryDO.getProdList();
		
		List<ProductTO> prodTOsList=new ArrayList<ProductTO>();
		
		for(Product prodDO:prodDOsList) {
			ProductTO prodTO=new ProductTO();
			
			prodTO.setProductId(prodDO.getProductId());
			
			prodTO.setProductName(prodDO.getProductName());
			
			prodTO.setCategoryId(categoryDO.getCategoryId());
			
			prodTOsList.add(prodTO);
			
		}
		
		categoryTO.setProductList(prodTOsList);
		
	}
	
	public void getProductToFromDO(ProductTO prodTO,Product prodDO) {
		prodTO.setProductId(prodDO.getProductId());
		
		prodTO.setProductName(prodDO.getProductName());
		
		prodTO.setCategoryId(prodDO.getCategory().getCategoryId());
	}
	
	
}
