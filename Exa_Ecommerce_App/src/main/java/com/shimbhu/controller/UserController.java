package com.shimbhu.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shimbhu.exceptions.CustomerException;
import com.shimbhu.exceptions.LoginException;
import com.shimbhu.exceptions.OrderException;
import com.shimbhu.exceptions.ProductException;
import com.shimbhu.model.Customer;
import com.shimbhu.model.CustomerDTO;
import com.shimbhu.model.OrderDTO;
import com.shimbhu.model.Orders;
import com.shimbhu.model.Product;
import com.shimbhu.service.CustomerService;
import com.shimbhu.service.LoginService;
import com.shimbhu.service.OrderService;
import com.shimbhu.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class UserController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private LoginService lService;
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("customer/login")
	public ResponseEntity<String> logInCustomer(@RequestBody CustomerDTO dto) throws LoginException
	{
		String message = lService.logIntoAccount(dto);
		
		return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("customer/logout/{key}")
	public ResponseEntity<String> logoutCustomer(@PathVariable("key") String key) throws LoginException
	{
		String message = lService.logOutFromAccount(key);
		
		return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
	}
	
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> CreateNewAccount(@Valid @RequestBody Customer customer) throws CustomerException
	{
		System.out.println("customer  : "+customer);
		Customer user = customerService.addCustomer(customer);
		
		return new ResponseEntity<Customer>(user,HttpStatus.CREATED);
	}
	
	@PutMapping("/customers/{key}")
	public ResponseEntity<Customer> updateAccount(@Valid @RequestBody Customer customer,@PathVariable("key") String key) throws CustomerException
	{
		Customer user = customerService.upadateCustomer(customer,key);
		
		return new ResponseEntity<Customer>(user,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/customers/{email}/{key}")
	public ResponseEntity<Customer> deleteAccount(@PathVariable("email") String email,@PathVariable("key") String key) throws CustomerException
	{
		Customer user = customerService.deleteCustomer(email,key);
		
		return new ResponseEntity<Customer>(user,HttpStatus.OK);
	}
	

	@GetMapping("/getProductById/{id}/{key}")
	public ResponseEntity<Product> viewProductByIdHandler(@PathVariable("id") Integer id,@PathVariable("key") String key) throws ProductException, LoginException{
		Product product = customerService.viewProductById(id,key);
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@GetMapping("/allProduct/{key}")
	public ResponseEntity<List<Product>> viewAllProductsHandler(@PathVariable("key") String key) throws ProductException, LoginException{
		List<Product> plist = customerService.viewAllProduct(key);
		return new ResponseEntity<List<Product>>(plist,HttpStatus.OK);
	}
	
	
	@GetMapping("/allProductByName/{name}/{key}")
	public ResponseEntity<List<Product>> viewAllProductsByName(@PathVariable("name") String name,@PathVariable("key") String key) throws ProductException, LoginException{
		List<Product> plist = customerService.viewProductByProductName(name,key);
		return new ResponseEntity<List<Product>>(plist,HttpStatus.OK);
	}
    
	@PostMapping("/orders/{key}")
	public ResponseEntity<Orders> addOrder(@Valid @RequestBody OrderDTO dto,@PathVariable("key") String key) throws OrderException, CustomerException
	{
		
		Orders details = orderService.addOrder(dto,key);
		
		return new ResponseEntity<Orders>(details,HttpStatus.CREATED);
	}
	
	@PutMapping("/orders/{orderId}/{key}")
	public ResponseEntity<Orders> updateOrder(@Valid @RequestBody OrderDTO dto,@PathVariable("orderId") Integer id,@PathVariable("key") String key) throws OrderException, CustomerException
	{
		
		Orders details = orderService.updateOrder(dto,id,key);
		
		return new ResponseEntity<Orders>(details,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/orders/{orderId}/{key}")
	public ResponseEntity<String> deleteOrder(@PathVariable("orderId") Integer id,@PathVariable("key") String key) throws OrderException, CustomerException
	{
		
		String details = orderService.deleteOrder(id, key);
		
		return new ResponseEntity<String>(details,HttpStatus.CREATED);
	}
	
	@GetMapping("/orders/{orderId}/{key}")
	public ResponseEntity<Orders> viewOrder(@PathVariable("orderId") Integer id,@PathVariable("key") String key) throws OrderException, CustomerException
	{
		
		Orders details = orderService.viewOrder(id, key);
		
		return new ResponseEntity<Orders>(details,HttpStatus.CREATED);
	}
	
	@GetMapping("/orders/{key}")
	public ResponseEntity<List<Orders> > viewAllOrder(@PathVariable("key") String key) throws OrderException, CustomerException
	{
		
		List<Orders> details = orderService.viewAllOrder(key);
		
		return new ResponseEntity<List<Orders> >(details,HttpStatus.CREATED);
	}
	
	@GetMapping("/AllProductsByCategory/{category}/{key}")
	public ResponseEntity<List<Product>> viewAllProductsByCategory(@PathVariable("category") String category,@PathVariable("key") String key) throws ProductException, LoginException{
		List<Product> plist = customerService.viewProductByCategory(category,key);
		return new ResponseEntity<List<Product>>(plist,HttpStatus.OK);
	}
	
}