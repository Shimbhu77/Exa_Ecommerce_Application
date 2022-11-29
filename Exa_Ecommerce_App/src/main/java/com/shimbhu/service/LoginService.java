package com.shimbhu.service;

import com.shimbhu.exceptions.LoginException;
import com.shimbhu.model.AdminDTO;
import com.shimbhu.model.CustomerDTO;

public interface LoginService {

	public String logIntoAccount(CustomerDTO dto)throws LoginException;

	public String logOutFromAccount(String key)throws LoginException;
	
	public String logAdminIntoAccount(AdminDTO dto)throws LoginException;

	public String logOutAdminFromAccount(String key)throws LoginException;
	
}
