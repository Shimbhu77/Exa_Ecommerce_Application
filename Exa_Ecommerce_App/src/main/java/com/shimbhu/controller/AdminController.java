package com.shimbhu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shimbhu.exceptions.LoginException;
import com.shimbhu.exceptions.ProductException;
import com.shimbhu.model.AdminDTO;
import com.shimbhu.model.Product; 
import com.shimbhu.service.LoginService;
import com.shimbhu.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/Admin")
public class AdminController {

	@Autowired
	private LoginService lService;
	
	@Autowired
	private ProductService pService;
	
	
	@PostMapping("/login")
	public ResponseEntity<String> logInAdmin(@RequestBody AdminDTO dto) throws LoginException
	{
		String message = lService.logAdminIntoAccount(dto);
		
		return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/logout/{key}")
	public ResponseEntity<String> logoutAdmin(@PathVariable("key") String key) throws LoginException
	{
		String message = lService.logOutAdminFromAccount(key);
		
		return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/product/{key}")
	public ResponseEntity<Product> addNewProductHandler(@RequestBody Product product,@PathVariable("key") String key) throws ProductException,LoginException{
		
		Product saveProduct = pService.addProduct(product,key);
		return new ResponseEntity<Product>(saveProduct,HttpStatus.CREATED);
		
		
	}
	@PutMapping("/Product/{key}")
	public ResponseEntity<Product> updateProductHandler(@RequestBody Product product,@PathVariable("key") String key) throws ProductException, LoginException{
		
		Product updatedProduct = pService.updateProduct(product,key);
		return new ResponseEntity<Product>(updatedProduct,HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("/Product/{id}/{key}")
	public ResponseEntity<Product> deleteProductByIdHandler(@PathVariable("id") Integer id,@PathVariable("key") String key) throws ProductException, LoginException{
		Product product=	pService.deleteProduct(id,key);
	  return new ResponseEntity<Product>(product,HttpStatus.ACCEPTED);
	}
	
	
	@GetMapping("/getProductById/{id}/{key}")
	public ResponseEntity<Product> viewProductByIdHandler(@PathVariable("id") Integer id,@PathVariable("key") String key) throws ProductException, LoginException{
		Product product = pService.viewProductById(id,key);
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	@GetMapping("/allProduct/{key}")
	public ResponseEntity<List<Product>> viewAllProductsHandler(@PathVariable("key") String key) throws ProductException, LoginException{
		List<Product> plist = pService.viewAllProduct(key);
		return new ResponseEntity<List<Product>>(plist,HttpStatus.OK);
	}
	
	
	@GetMapping("/allProductByName/{name}/{key}")
	public ResponseEntity<List<Product>> viewAllProductsByName(@PathVariable("name") String name,@PathVariable("key") String key) throws ProductException, LoginException{
		List<Product> plist = pService.viewProductByProductName(name,key);
		return new ResponseEntity<List<Product>>(plist,HttpStatus.OK);
	}
	
	@GetMapping("/AllProductsByCategory/{category}/{key}")
	public ResponseEntity<List<Product>> viewAllProductsByCategory(@PathVariable("category") String category,@PathVariable("key") String key) throws ProductException, LoginException{
		List<Product> plist = pService.viewProductByCategory(category,key);
		return new ResponseEntity<List<Product>>(plist,HttpStatus.OK);
	}
	
	
	
}
