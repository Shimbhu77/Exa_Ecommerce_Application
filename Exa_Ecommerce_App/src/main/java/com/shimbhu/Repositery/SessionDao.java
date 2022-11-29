package com.shimbhu.Repositery;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shimbhu.model.CurrentUserSession;

@Repository
public interface SessionDao extends JpaRepository<CurrentUserSession, Integer> {

	public  CurrentUserSession  findByUuid(String uuid);


	
}
