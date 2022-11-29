package com.shimbhu.Repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shimbhu.model.Admin;
@Repository
public interface AdminDao extends JpaRepository<Admin,Integer> {

	public Admin findByAdminEmail(String adminEmail);
}
