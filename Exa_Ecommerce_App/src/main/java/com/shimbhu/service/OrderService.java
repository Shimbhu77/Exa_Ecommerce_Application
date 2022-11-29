package com.shimbhu.service;

import java.util.List;

import com.shimbhu.exceptions.CustomerException;
import com.shimbhu.exceptions.OrderException;
import com.shimbhu.model.OrderDTO;
import com.shimbhu.model.Orders;

public interface OrderService {

	public Orders addOrder(OrderDTO orders,String key) throws OrderException, CustomerException;
	public Orders updateOrder(OrderDTO orders,Integer orderId,String key) throws OrderException, CustomerException;
	public String deleteOrder(Integer orderId,String key) throws OrderException, CustomerException;
	public Orders viewOrder(Integer orderId,String key) throws OrderException, CustomerException;
	public List<Orders> viewAllOrder(String key) throws OrderException, CustomerException;
}
