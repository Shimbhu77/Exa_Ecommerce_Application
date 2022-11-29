package com.shimbhu.service;

import java.util.List;

import com.shimbhu.exceptions.CustomerException;
import com.shimbhu.exceptions.LoginException;
import com.shimbhu.exceptions.ProductException;
import com.shimbhu.model.Customer;
import com.shimbhu.model.Product;

public interface CustomerService {

	public Customer addCustomer(Customer customer) throws CustomerException;
	public Customer upadateCustomer(Customer tenant, String key) throws CustomerException;
	public Customer deleteCustomer(String email,String Key) throws CustomerException;
	public Product viewProductById(Integer productId,String key) throws ProductException,LoginException;
	public List<Product> viewProductByProductName(String name,String key) throws ProductException,LoginException;
	public List<Product> viewProductByCategory(String Category,String key) throws ProductException,LoginException;
	public List<Product> viewAllProduct(String key) throws ProductException,LoginException;
}