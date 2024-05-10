package com.kodnest.tunehub.service;

import java.util.List;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;

public interface PlaylistService {

	List<Song> fetchAllSongs();

	void addPlaylist(Playlist playlist);

	boolean existsByName(Playlist playlist);

	List<Playlist> fetchAllPlaylists();

}
