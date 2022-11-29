package com.shimbhu.Repositery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shimbhu.model.Product;

public interface ProductDao  extends JpaRepository<Product, Integer>{

	public List<Product> findByProductName(String name);
	public List<Product> findByCategory(String category);
	
}
