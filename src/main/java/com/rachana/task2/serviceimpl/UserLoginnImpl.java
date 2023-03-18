package com.rachana.task2.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rachana.task2.model.User;
import com.rachana.task2.repository.UserRepository;
import com.rachana.task2.service.UserLogin;

@Service
public class UserLoginnImpl implements UserLogin {
    @Autowired
	private UserRepository userRepo;
	
	@Override
	public boolean checkUser(User user) {
		boolean flag = false;
		
		User newUser = userRepo.checkUser(user.getEmail(), user.getPassword());
		
		if(newUser!=null)
		{
			flag=true;
		}
		
		return flag;

	}

}



