package com.kodnest.tunehub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.repository.PlaylistRepository;
import com.kodnest.tunehub.repository.SongRepository;
import com.kodnest.tunehub.service.PlaylistService;

@Service
public class PlaylistServiceimpl implements PlaylistService {

	@Autowired
	PlaylistRepository playlistRepository;
	
	@Autowired
	SongRepository songRepository;

	@Override
	public List<Song> fetchAllSongs() {
		List<Song> play=songRepository.findAll();
		return play;
	}

	@Override
	public void addPlaylist(Playlist playlist) {
		
		playlistRepository.save(playlist);
	}

	@Override
	public boolean existsByName(Playlist playlist) {
		Playlist exname=playlistRepository.findByName(playlist.getName());
		if(exname!=null) {
			System.out.println("present");
			return true;
		}else {
			System.out.println("absent");
			return false;
		}
	}

	@Override
	public List<Playlist> fetchAllPlaylists() {
		List<Playlist> playlists= playlistRepository.findAll();
		return playlists;
	}

	
}
