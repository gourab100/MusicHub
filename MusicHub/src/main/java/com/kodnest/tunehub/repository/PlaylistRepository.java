package com.kodnest.tunehub.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Integer> {

	Playlist findByName(String name);
	
}
