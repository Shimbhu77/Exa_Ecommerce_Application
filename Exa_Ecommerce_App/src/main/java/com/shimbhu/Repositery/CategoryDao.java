package com.shimbhu.Repositery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shimbhu.model.Category;
import com.shimbhu.model.Product;

@Repository
public interface CategoryDao extends JpaRepository<Category, Integer> {
	public Category findByCategory(String category);
}
