package com.shimbhu.Repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shimbhu.model.CurrentAdminSession;

@Repository
public interface AdminSessionDao extends JpaRepository<CurrentAdminSession, Integer>{

	public  CurrentAdminSession  findByUuid(String uuid);
}

