package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.service.SongService;

@Controller
public class SongController {

	@Autowired
	SongService songService;
	
	@PostMapping("/addsongs")
	public String addSong(@ModelAttribute Song song) {
		
		if(songService.existName(song)) {
			System.out.println("Duplicate song");
		}else {
		System.out.println("Song added successfully");
		songService.addSong(song);
		}
		return "adminhome";
	}
	
	
	@GetMapping("/viewsongs")
	public String viewSongs(Model model) {
		List<Song> songlist= songService.fetchAllSongs();
		model.addAttribute("songs", songlist);
		return "viewsongs";
	}
	
}
