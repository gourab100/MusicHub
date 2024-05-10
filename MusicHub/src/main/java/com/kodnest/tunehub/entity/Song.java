package com.kodnest.tunehub.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Song {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
    private int id;
	private String name;
	private String artist;
	private String genre;
	private String Link;
	@ManyToMany
	private List<Playlist> playlists;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getLink() {
		return Link;
	}
	public void setLink(String link) {
		Link = link;
	}
	public List<Playlist> getPlaylists() {
		return playlists;
	}
	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}
	public Song(int id, String name, String artist, String genre, String link, List<Playlist> playlists) {
		super();
		this.id = id;
		this.name = name;
		this.artist = artist;
		this.genre = genre;
		Link = link;
		this.playlists = playlists;
	}
	public Song() {
		super();
	}
	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", artist=" + artist + ", genre=" + genre + ", Link=" + Link
				+ ", playlists=" + playlists + "]";
	}

}