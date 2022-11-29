package com.shimbhu.service;

import java.util.List;

import com.shimbhu.exceptions.LoginException;
import com.shimbhu.exceptions.ProductException;
import com.shimbhu.model.Product;

public interface ProductService {

	public Product addProduct(Product product,String key) throws ProductException,LoginException; 
	public Product updateProduct(Product product,String key) throws ProductException,LoginException;
	public Product deleteProduct(Integer productId,String key) throws ProductException,LoginException;
	public Product viewProductById(Integer productId,String key) throws ProductException,LoginException;
	public List<Product> viewProductByProductName(String name,String key) throws ProductException,LoginException;
	public List<Product> viewProductByCategory(String Category,String key) throws ProductException,LoginException;
	public List<Product> viewAllProduct(String key) throws ProductException,LoginException;
	
}
