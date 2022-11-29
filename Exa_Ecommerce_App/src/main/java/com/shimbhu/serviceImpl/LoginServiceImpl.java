package com.shimbhu.serviceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shimbhu.Repositery.AdminDao;
import com.shimbhu.Repositery.AdminSessionDao;
import com.shimbhu.Repositery.CustomerDao;
import com.shimbhu.Repositery.SessionDao;
import com.shimbhu.exceptions.LoginException;
import com.shimbhu.model.Admin;
import com.shimbhu.model.AdminDTO;
import com.shimbhu.model.CurrentAdminSession;
import com.shimbhu.model.CurrentUserSession;
import com.shimbhu.model.Customer;
import com.shimbhu.model.CustomerDTO;
import com.shimbhu.service.LoginService;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private SessionDao sessionDao;

	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private AdminSessionDao adminSessionDao;
	
	
	@Override
	public String logIntoAccount(CustomerDTO dto) throws LoginException {
		
		Customer user = customerDao.findByCustomerEmail(dto.getCustomerEmail());
		
		if(user==null)
		{
			throw new LoginException("Please Enter valid Email");
		}
		
		Optional<CurrentUserSession> opt = sessionDao.findById(user.getCustomerId());
		
		if(opt.isPresent())
		{
			throw new LoginException("User Already logged in ");
		}
		
		if(user.getPassword().equals(dto.getCustomerPassword()))
		{
			String key = RandomString.make(7);
			
			CurrentUserSession cus = new CurrentUserSession(user.getCustomerId(),key,LocalDateTime.now());
			
			sessionDao.save(cus);
			
			return "Login succesfully , Unique key :  "+key+" , And details : "+user.toString();
		}
		else
		{
			throw new LoginException("Please Enter a valid password.");
		}
	}

	@Override
	public String logOutFromAccount(String key) throws LoginException {
		
	     CurrentUserSession cus =	sessionDao.findByUuid(key);
	     
	     if(cus==null)
	     {
	    	 throw new LoginException("User not Login with this Email.");
	     }
	     
	     sessionDao.delete(cus);
	     
	     return "Logged out Successfully.";
	}

	@Override
	public String logAdminIntoAccount(AdminDTO dto) throws LoginException {

		Admin user = adminDao.findByAdminEmail(dto.getAdminEmail());
		
		if(user==null)
		{
			throw new LoginException("Please Enter valid Email");
		}
		
		Optional<CurrentAdminSession> opt = adminSessionDao.findById(user.getAdminId());
		
		if(opt.isPresent())
		{
			throw new LoginException("Admin Already logged in ");
		}
		
		if(user.getAdminPassword().equals(dto.getAdminPassword()))
		{
			String key = RandomString.make(7);
			
			CurrentAdminSession cus = new CurrentAdminSession(user.getAdminId(),key,LocalDateTime.now());
			
			adminSessionDao.save(cus);
			
		    return "Login succesfully , Unique key :  "+key+" , And details : "+user.toString();
		}
		else
		{
			throw new LoginException("Please Enter a valid password.");
		}
	}

	@Override
	public String logOutAdminFromAccount(String key) throws LoginException {

		CurrentAdminSession cus =	adminSessionDao.findByUuid(key);
	     
	     if(cus==null)
	     {
	    	 throw new LoginException("Admin not Login with this Email.");
	     }
	     
	     adminSessionDao.delete(cus);
	     
	     return "Logged out Successfully.";
	}
}
