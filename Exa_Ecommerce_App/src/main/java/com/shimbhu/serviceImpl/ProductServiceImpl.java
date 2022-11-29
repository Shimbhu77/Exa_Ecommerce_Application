package com.shimbhu.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shimbhu.Repositery.AdminDao;
import com.shimbhu.Repositery.AdminSessionDao;
import com.shimbhu.Repositery.CategoryDao;
import com.shimbhu.Repositery.ProductDao;
import com.shimbhu.exceptions.LoginException;
import com.shimbhu.exceptions.ProductException;
import com.shimbhu.model.CurrentAdminSession;
import com.shimbhu.model.Product;
import com.shimbhu.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	AdminDao adao;
	
	@Autowired
	AdminSessionDao adminSessionDao;
	
	@Autowired
	ProductDao pDao;
	
	@Autowired
	CategoryDao cDao;
	
	@Override
	public Product addProduct(Product product, String key) throws ProductException, LoginException {
		 
		    CurrentAdminSession  loggeduser= adminSessionDao.findByUuid(key);
			
			if(loggeduser==null)
			{
				throw new LoginException("Please Enter a Valid Key to add a product...");
			}
			
			Optional<Product> p = pDao.findById(product.getProductId());
			if(p.isPresent()) {
				throw new ProductException("Product already exists with this id "+product.getProductId());
			}
			else
			{

				Product saveProduct = pDao.save(product);
				return saveProduct;
			}
	}

	@Override
	public Product updateProduct(Product product, String key) throws ProductException, LoginException {
		
		CurrentAdminSession  loggeduser= adminSessionDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new LoginException("Please Enter a Valid Key to update a product...");
		}
		
		Optional<Product> p = pDao.findById(product.getProductId());
		if(p.isPresent()) 
		{
			Product saveProduct = pDao.save(product);
			return saveProduct;
		}
		throw new ProductException("Product not exists with this id "+product.getProductId());
	}

	@Override
	public Product deleteProduct(Integer productId, String key) throws ProductException, LoginException {
		 
		    CurrentAdminSession  loggeduser= adminSessionDao.findByUuid(key);
			
			if(loggeduser==null)
			{
				throw new LoginException("Please Enter a Valid Key to delete a product...");
			}
			
			Optional<Product> p = pDao.findById(productId);
			if(p.isPresent()) 
			{
				pDao.delete(p.get());
				return p.get();
			}
			throw new ProductException("Product not exists with this id "+productId);
	}

	@Override
	public Product viewProductById(Integer productId, String key) throws ProductException, LoginException {
         CurrentAdminSession  loggeduser= adminSessionDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new LoginException("Please Enter a Valid Key to view a product...");
		}
		
		Optional<Product> p = pDao.findById(productId);
		if(p.isPresent()) 
		{
			return p.get();
		}
		throw new ProductException("Product not exists with this id "+productId);
	}

	@Override
	public List<Product> viewProductByProductName(String name, String key) throws ProductException, LoginException {
		 
		    CurrentAdminSession  loggeduser= adminSessionDao.findByUuid(key);
			
			if(loggeduser==null)
			{
				throw new LoginException("Please Enter a Valid Key to view a product...");
			}
			
			List<Product> p = pDao.findByProductName(name);
			if(p.size()==0) 
			{
				throw new ProductException("No Products found with this name : "+name);
			}
			return p;
	}

	@Override
	public List<Product> viewProductByCategory(String Category, String key) throws ProductException, LoginException {

	    CurrentAdminSession  loggeduser= adminSessionDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new LoginException("Please Enter a Valid Key to view a product...");
		}
		
		com.shimbhu.model.Category category = cDao.findByCategory(Category);
		
		if(category==null)
		{
			throw new ProductException("No Products found with this category : "+Category);
		}
		
		List<Product> p = pDao.findAll();
		List<Product> list = new ArrayList<>();
		
		for(Product pr:p)
		{
			if(pr.getCategory().getCategoryId()==category.getCategoryId())
			{
				list.add(pr);
			}
		}
		if(list.size()==0) 
		{
			throw new ProductException("No Products found with this category : "+Category);
		}
		return list;
	}

	@Override
	public List<Product> viewAllProduct(String key) throws ProductException, LoginException {

	    CurrentAdminSession  loggeduser= adminSessionDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new LoginException("Please Enter a Valid Key to view All  products...");
		}
		
		List<Product> p = pDao.findAll();
		if(p.size()==0) 
		{
			throw new ProductException("No Products available ...");
		}
		return p;
	}

}
