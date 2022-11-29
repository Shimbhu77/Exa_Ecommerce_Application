package com.shimbhu.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderDTO {

	@Email(message = "please enter a valid email for placing the order")
	private String customerEmail;
	@Min(value = 1,message = "productId for products which you want to buy.")
	private Integer productId;
	
	@Min(value = 1,message = "product quantity at least 1 for placing the order")
	private Integer quantity;
	
	@Size(min=3,max=20,message = "please enter transaction Mode UPI or Wallet or Net Banking")
	private String transactionMode;
	
}
