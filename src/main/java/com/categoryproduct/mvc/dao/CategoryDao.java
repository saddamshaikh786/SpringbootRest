package com.categoryproduct.mvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.categoryproduct.mvc.model.Category;

public interface CategoryDao extends JpaRepository<Category, Integer> {
	
}
