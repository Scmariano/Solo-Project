package com.stephen.soloproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.stephen.soloproject.models.Song;
import com.stephen.soloproject.repositories.SongRepo;

@Service
public class SongServ {
	@Autowired SongRepo songRepo;
	
	public List<Song>allSongs(){
		return songRepo.findAll();
	}
	
	public Song createSong(Song song) {
		return songRepo.save(song);
	}
	
	public Song findSongId(Long id) {
		Optional<Song>optSong = songRepo.findById(id);
		if(optSong.isPresent()) {
			return optSong.get();
		}else {
			return null;
		}
	}
	
	public Song updateSong(Song song) {
		return songRepo.save(song);
	}
	
	public void deleteSongId(Long id) {
	    songRepo.deleteById(id);
	}
	
	public void deleteSong(Song song) {
		songRepo.delete(song);
	} 
	
	public List<Song>setSongs(Long setId){
	    return songRepo.findBySongsSetId(setId);
	}
	
	
	
	
	
}
