package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.service.CustomerService;
import com.kodnest.tunehub.service.PlaylistService;

import jakarta.servlet.http.HttpSession;
//@CrossOrigin("*")
//@RestController
@Controller
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	PlaylistService playlistService;
	
	@GetMapping("/playsongs")
	public String playSongs(Model model) {
		boolean premium=false;
		if(premium) {
			List<Song> songlist= customerService.fetchAllSongs();
			model.addAttribute("songs", songlist);
			return "viewsongs";
		}else {
			return "pay";
		}
		
	}
	
	@GetMapping("/viewplaylists")
	public String viewplaylists(Model model) {
		boolean  premium=false;
		if(premium) {
			List<Playlist> playlist= playlistService.fetchAllPlaylists();
			model.addAttribute("playlists", playlist);
			return "viewplaylist";
		}else {
			return "pay";
		}
		
	}
	
	

}
