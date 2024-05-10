package com.kodnest.tunehub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.repository.CustomerRepository;
import com.kodnest.tunehub.service.CustomerService;

@Service
public class CustomerServiceimpl implements CustomerService{
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public List<Song> fetchAllSongs() {
		List<Song> songs= customerRepository.findAll();
		return songs;
	}


}
