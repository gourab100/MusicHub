package com.kodnest.tunehub.service;


import java.util.List;

import com.kodnest.tunehub.entity.User;

public interface UserService {

	void userdata(User user);

	boolean emailExist(User user);

	boolean validatecredential(String email, String password);

	String getRole(String email);

	User getUser(String mail);

	void updateUser(User user);



}
