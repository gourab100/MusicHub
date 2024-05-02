package com.kodnest.tunehub.serviceimpl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.User;
import com.kodnest.tunehub.repository.UserRepository;
import com.kodnest.tunehub.service.UserService;

@Service
public class UserServiceimpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void userdata(User user) {
		userRepository.save(user);
		
	}

	@Override
	public boolean emailExist(User user) {
	User exuser=userRepository.findByEmail(user.getEmail());
		if(exuser!=null){
			System.out.println("present");
			return true;
		}else {
			System.out.println("Absent");
			return false;
		}
		}




	@Override
	public boolean validatecredential(String email, String password) {
		
		User user=userRepository.findByEmail(email);
		String dbpwd=user.getPassword(); 
		if(password.equals(dbpwd)) {
			return true;
		}else {
			return false;
		}
	}


	@Override
	public String getRole(String email) {
		User user=userRepository.findByEmail(email);
		return user.getRole();
		
	}

	@Override
	public User getUser(String mail) {
		return userRepository.findByEmail(mail);
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
		
	}
	
	
	


		
	}

	

//	@Override
//	public boolean existsByEmail(String email) {
//		// TODO Auto-generated method stub
//		return userRepository.existsByEmail(email);
//	}

	
