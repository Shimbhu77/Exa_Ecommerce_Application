package com.shimbhu.Repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shimbhu.model.Customer;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {

	public Customer findByCustomerEmail(String customerEmail);

}
