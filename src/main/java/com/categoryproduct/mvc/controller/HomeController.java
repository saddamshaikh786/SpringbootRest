package com.categoryproduct.mvc.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.categoryproduct.mvc.dao.CategoryDao;
import com.categoryproduct.mvc.dto.CategoryTO;
import com.categoryproduct.mvc.dto.ProductTO;
import com.categoryproduct.mvc.model.Category;
import com.categoryproduct.mvc.model.Product;
import com.categoryproduct.mvc.service.HomeService;

@RestController
@RequestMapping(value="/api")
public class HomeController {
	
	@Autowired
	HomeService homeService;
	
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello!!";
	}
	
	@GetMapping("/categories")
	public List<CategoryTO> getAllCategory() {
		return homeService.getAllCategories();
	}
	
	@GetMapping("/products")
	public List<ProductTO> getAllProducts(){
		return homeService.getAllProducts();
	}

	@GetMapping("/categories/{id}")
	public CategoryTO getCategoryById(@PathVariable Integer id) {
		CategoryTO categoryTO=homeService.getCategoryById(id);
		return  categoryTO!=null?categoryTO:new CategoryTO();
	}
	
	@GetMapping("/products/{id}")
	public ProductTO getProductById(@PathVariable Integer id) {
		ProductTO productTO= homeService.getProductById(id);
		
		return productTO!=null?productTO:new ProductTO();
	}
	
	@DeleteMapping("/categories/{id}")
	public String deleteCategoryById(@PathVariable Integer id) {
		boolean flag=homeService.deleteCategoryById(id);
		
		if(flag) return "Category deleted successfully!!";
		
		return "Failed to delete provided category";
	}
	
	@DeleteMapping("/products/{id}")
	public String deleteProductById(@PathVariable Integer id) {
		boolean flag=homeService.deleteProductById(id);
		
		if(flag) return "Product deleted successfully";
		
		return "Failed to delete provided product";
	}
	
	@PostMapping("/categories")
	public String saveCategory(@RequestBody Map<String, Object> inputMap) {
		
		boolean flag=homeService.saveCatogory(inputMap);
		
		if(flag) return "Category Created successfully";
		
		return "Failed to crate provided category";
	}
	
	@PostMapping("/products")
	public String saveProduct(@RequestBody Map<String, Object> inputMap) {
		
		boolean flag=homeService.saveProduct(inputMap);
		
		if(flag) return "Product Created successfully";
		
		return "Failed to crate provided product";
	}
}
