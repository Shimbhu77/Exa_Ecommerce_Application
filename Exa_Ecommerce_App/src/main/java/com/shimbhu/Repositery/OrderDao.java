package com.shimbhu.Repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shimbhu.model.Orders;

@Repository
public interface OrderDao extends JpaRepository<Orders, Integer> {

	
}