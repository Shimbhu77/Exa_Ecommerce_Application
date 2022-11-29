package com.shimbhu.serviceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shimbhu.Repositery.CustomerDao;
import com.shimbhu.Repositery.OrderDao;
import com.shimbhu.Repositery.ProductDao;
import com.shimbhu.Repositery.SessionDao;
import com.shimbhu.exceptions.CustomerException;
import com.shimbhu.exceptions.OrderException;
import com.shimbhu.model.CurrentUserSession;
import com.shimbhu.model.Customer;
import com.shimbhu.model.OrderDTO;
import com.shimbhu.model.Orders;
import com.shimbhu.model.Product;
import com.shimbhu.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private SessionDao sessionDao;
	
	@Autowired
	private ProductDao productDao;
	
	
	@Override
	public Orders addOrder(OrderDTO orders, String key) throws CustomerException, OrderException{

		CurrentUserSession  loggeduser= sessionDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new CustomerException("Please login first and Enter a Valid Key to placing the order.");
		}
		
		  Customer customer=  customerDao.findByCustomerEmail(orders.getCustomerEmail());
		
		  if(customer==null)
		  {
			  throw new CustomerException("Invalid Customer Email details , Please login first for placing the order. ");
		  }
		  
		if(customer.getCustomerId()==loggeduser.getUserId())
		{
			  Orders orderDetails = new Orders();
			  orderDetails.setLocalDate(LocalDate.now());
			  if(orders.getTransactionMode()==null)
			  {
				  throw new OrderException("please enter payment gateway like UPI or Waller or Net Banking.");
			  }
			  if(orders.getQuantity()<=0)
			  {
				  throw new OrderException("please add one for placing the order.");
			  }
			  orderDetails.setTransactionMode(orders.getTransactionMode());
			  orderDetails.setQuantity(orders.getQuantity());
			  orderDetails.setCustomerId(customer.getCustomerId());
			  double total=0;
			
			 Optional<Product> product =productDao.findById(orders.getProductId());
			 
			 if(!(product.isPresent()))
			 {
				 throw new OrderException("Invalid product Id , put right Product Id , Please add one product to place the order.");
			 }
			
			 if(product.isPresent())
			 {
				 
				 if(orders.getQuantity()<=product.get().getProductStock())
				 {
					 
					 product.get().setProductStock(product.get().getProductStock()-orders.getQuantity());
					 productDao.save(product.get());
					 total=total+orders.getQuantity()*product.get().getProductPrice();
				 }
				 else
				  throw new OrderException("Insufficient stock for products ");
				 System.out.println("*********************");
				
			 }
			 
			 
			 
			 orderDetails.setTotalCost(total);
			 System.out.println("order add is end. *************===========*********"+orderDetails);
			 orderDao.save(orderDetails);
			 System.out.println("order return state. *************===========*********"+orderDetails);
			 return orderDetails;
			 
		}
		
		throw new CustomerException("Please login first for placing the order. ");
		
	}

	@Override
	public Orders updateOrder(OrderDTO orders,Integer orderId, String key) throws OrderException, CustomerException {
		
        CurrentUserSession  loggeduser= sessionDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new CustomerException("Please login first and Enter a Valid Key to updating the order.");
		}
		
		  Customer customer=  customerDao.findByCustomerEmail(orders.getCustomerEmail());
		
		  if(customer==null)
		  {
			  throw new CustomerException("Invalid Customer Email details , Please login first for updating the order. ");
		  }
		  
		  if(customer.getCustomerId()==loggeduser.getUserId())
			{
			  
			      Optional<Orders> opt= orderDao.findById(orderId);
			      if(opt.isPresent())
			      {
			    	  Orders orderDetails =opt.get();
					  orderDetails.setLocalDate(LocalDate.now());
					  if(orders.getTransactionMode()==null)
					  {
						  throw new OrderException("please enter payment gateway like UPI or Wallet or Net Banking.");
					  }
					  if(orders.getQuantity()<=0)
					  {
						  throw new OrderException("please add one product for updating the order.");
					  }
					  orderDetails.setTransactionMode(orders.getTransactionMode());
					 
					  orderDetails.setCustomerId(customer.getCustomerId());
					  double total=0;
					 Optional<Product> product =productDao.findById(orders.getProductId());
					 
					 
					 if(!product.isPresent())
					 {
						 throw new OrderException("Invalid product Id , put right Product Id , Please add one product to place the order.");
					 }
					 
					 
					
					 if(product.isPresent())
					 {
						 
						
						 
						 if(orders.getQuantity()<=product.get().getProductStock())
						 {
							 
							 
							 Product p =product.get();
							 p.setProductStock(product.get().getProductStock()+orderDetails.getQuantity()-orders.getQuantity());
							 productDao.save(p);
							 total=total+orders.getQuantity()*product.get().getProductPrice();
						 }
						 else throw new OrderException("Insufficient stock for products ");
						 
						
					 }
					 
					 
					
					 orderDetails.setQuantity(orders.getQuantity());
					 orderDetails.setTotalCost(total);
					 orderDao.save(orderDetails);
					 return orderDetails;
			      }
			  
				  
				 
			}
			
			throw new CustomerException("Please login first for updating the order. "); 
	}

	@Override
	public String deleteOrder(Integer orderId, String key) throws OrderException, CustomerException {
		
		 CurrentUserSession  loggeduser= sessionDao.findByUuid(key);
			
			if(loggeduser==null)
			{
				throw new CustomerException("Please login first and Enter a Valid Key to deleting the order.");
			}
			
			  Optional<Customer> opt=  customerDao.findById(loggeduser.getUserId());
			  
			  Optional<Orders> orders  = orderDao.findById(orderId);
			
			  if(opt.isPresent())
			  {
				  if(orders.isPresent())
				  {
					  if(orders.get().getCustomerId()==opt.get().getCustomerId())
					  {
						  orderDao.delete(orders.get());
						  
						  return "Order deleted successfully.";
					  }
					  throw new CustomerException("invalid key , Please login first for deleting the order. ");
				  }
				  throw new OrderException("No order found with this order id "+orderId);
			  }
			  throw new CustomerException("Invalid key details , Please login first for deleting the order. ");
	}

	@Override
	public Orders viewOrder(Integer orderId, String key) throws OrderException, CustomerException {
		
		CurrentUserSession  loggeduser= sessionDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new CustomerException("Please login first and Enter a Valid Key to viewing the order.");
		}
		
		  Optional<Customer> opt=  customerDao.findById(loggeduser.getUserId());
		  
		  Optional<Orders> orders  = orderDao.findById(orderId);
		
		  if(opt.isPresent())
		  {
			  if(orders.isPresent())
			  {
				  if(orders.get().getCustomerId()==opt.get().getCustomerId())
				  {
					  return orders.get();
				  }
				  throw new CustomerException("invalid key , Please login first for viewing the order. ");
			  }
			  throw new OrderException("No order found with this order id "+orderId);
		  }
		  throw new CustomerException("Invalid key details , Please login first for viewing the order. ");
	}

	@Override
	public List<Orders> viewAllOrder(String key) throws OrderException, CustomerException {

		CurrentUserSession  loggeduser= sessionDao.findByUuid(key);
		
		if(loggeduser==null)
		{
			throw new CustomerException("Please login first and Enter a Valid Key to viewing the order.");
		}
		
		  Optional<Customer> opt=  customerDao.findById(loggeduser.getUserId());
		  
		  List<Orders> orders  = orderDao.findAll();
		
		  if(opt.isPresent())
		  {
			  if(orders.size()!=0)
			  {
				  List<Orders> myorders = new ArrayList<>();
				  for(Orders el : orders)
				  {
					  if(el.getCustomerId()==opt.get().getCustomerId())
					  {
						  myorders.add(el);
					  }
				  }
				  if(myorders.size()==0)
				  {
					  throw new OrderException("No order found in your account ");
				  }
				  
				  return myorders;
				  
			  }
			  throw new OrderException("No order found  ");
		  }
		  throw new CustomerException("Invalid key details , Please login first for viewing All your order. ");
	}
	
}
