package com.kodnest.tunehub.service;

import java.util.List;

import com.kodnest.tunehub.entity.Song;

public interface SongService {

	void addSong(Song song);

	boolean existName(Song song);

	List<Song> fetchAllSongs();

	void updateSong(Song song);


}
