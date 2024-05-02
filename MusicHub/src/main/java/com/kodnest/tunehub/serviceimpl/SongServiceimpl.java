package com.kodnest.tunehub.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.repository.SongRepository;
import com.kodnest.tunehub.service.SongService;

@Service
public class SongServiceimpl implements SongService{

	@Autowired
	SongRepository songRepository;

	@Override
	public void addSong(Song song) {
		songRepository.save(song);
		
	}

	@Override
	public boolean existName(Song song) {
		Song exname=songRepository.findByName(song.getName());
		if(exname!=null) {
			System.out.println("present");
			return true;
		}else {
			System.out.println("absent");
			return false;
		}
		
	}

	@Override
	public List<Song> fetchAllSongs() {
		List<Song> songs= songRepository.findAll();
		return songs;
	}

	@Override
	public void updateSong(Song song) {
		songRepository.save(song);	
	}

	

	

}
