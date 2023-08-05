package com.categoryproduct.mvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.categoryproduct.mvc.model.Product;

public interface ProductDao extends JpaRepository<Product, Integer>{

}
