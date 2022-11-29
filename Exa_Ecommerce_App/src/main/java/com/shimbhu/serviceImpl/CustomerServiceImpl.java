package com.shimbhu.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shimbhu.Repositery.CategoryDao;
import com.shimbhu.Repositery.CustomerDao;
import com.shimbhu.Repositery.ProductDao;
import com.shimbhu.Repositery.SessionDao;
import com.shimbhu.exceptions.CustomerException;
import com.shimbhu.exceptions.LoginException;
import com.shimbhu.exceptions.ProductException;
import com.shimbhu.model.CurrentAdminSession;
import com.shimbhu.model.CurrentUserSession;
import com.shimbhu.model.Customer;
import com.shimbhu.model.Product;
import com.shimbhu.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	ProductDao pDao;

	@Autowired
	CategoryDao cDao;
	
	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {
		
//		System.out.println("customer : ************************* "+customer);
		
		Customer existingCustomer = customerDao.findByCustomerEmail(customer.getCustomerEmail());
		
		//System.out.println("existingCustomer : ====================== "+existingCustomer);
		if(existingCustomer!=null)
		{
			throw new CustomerException("Customer already exists with this Email : "+customer.getCustomerEmail());
		}
		
		return customerDao.save(customer);
	}

	@Override
	public Customer upadateCustomer(Customer tenant ,String Key) throws CustomerException {
		
		CurrentUserSession  loggeduser= sessionDao.findByUuid(Key);
		
		if(loggeduser==null)
		{
			throw new CustomerException("Please Enter a Valid Key to update a customer.");
		}
		
		if(tenant.getCustomerId()==loggeduser.getUserId())
		{
			return customerDao.save(tenant);
			
		}
		
		throw new CustomerException("Invalid Customer details , Please login first for updating the customer. ");
		
	}

	@Override
	public Customer deleteCustomer(String email, String Key) throws CustomerException {
		
        CurrentUserSession  loggeduser= sessionDao.findByUuid(Key);
 		
		if(loggeduser==null)
		{
			throw new CustomerException("Please Enter a Valid Key to delete customer account.");
		}
		
		Customer existingCustomer = customerDao.findByCustomerEmail(email);
		
		if(existingCustomer==null)
		{
			throw  new CustomerException("Invalid Customer Email details ");
		}
		
		if(existingCustomer.getCustomerId()==loggeduser.getUserId())
		{
			 customerDao.delete(existingCustomer);
			 
			 sessionDao.delete(loggeduser);
			 return existingCustomer;
			
		}
		else throw new CustomerException("Please login first for deleting account.");
	}
	@Override
	public Product viewProductById(Integer id, String key) throws ProductException, LoginException {
         CurrentUserSession  loggeduser= sessionDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new LoginException("Please Enter a Valid Key to view a product...");
		}
		
		Optional<Product> p = pDao.findById(id);
		if(p.isPresent()) 
		{
			return p.get();
		}
		throw new ProductException("Product not exists with this id "+id);
	}

	@Override
	public List<Product> viewProductByProductName(String name, String key) throws ProductException, LoginException {
		 
		    CurrentUserSession  loggeduser= sessionDao.findByUuid(key);
			
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

	    CurrentUserSession  loggeduser= sessionDao.findByUuid(key);
		
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

		
		CurrentUserSession  loggeduser= sessionDao.findByUuid(key);
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
