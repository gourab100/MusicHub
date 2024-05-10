package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.service.PlaylistService;
import com.kodnest.tunehub.service.SongService;

@Controller
public class PlaylistController {

	@Autowired
	PlaylistService playlistService;

	@Autowired
	SongService songService;

	@GetMapping("/createplaylists")
	public String createPlaylist(Model model) {
		List<Song> songList= songService.fetchAllSongs();
		model.addAttribute("songs",songList);
		return "createplaylist";
	}

	@PostMapping("/addplaylist")
	public String addPlaylist(@ModelAttribute Playlist playlist,Model model) {
		if (playlistService.existsByName(playlist)) {
			System.out.println("Playlist with the same name already exists");

		}else {
			playlistService.addPlaylist(playlist);
		}
		List<Song> songs=playlist.getSongs();
		for(Song song: songs) {
			song.getPlaylists().add(playlist);
			songService.updateSong(song);
		}
		List<Song> songList = songService.fetchAllSongs();
	    model.addAttribute("songs", songList);
		return "adminhome"; 

	}
	@GetMapping("/viewplaylist")
	public String viewSongs(Model model) {
		List<Playlist> playlist= playlistService.fetchAllPlaylists();
		model.addAttribute("playlists", playlist);
		return "viewplaylist";
	}
}
