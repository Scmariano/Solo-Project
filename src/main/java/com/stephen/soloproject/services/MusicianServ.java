package com.stephen.soloproject.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stephen.soloproject.models.Musician;
import com.stephen.soloproject.repositories.MusicianRepo;

@Service
public class MusicianServ {
	@Autowired MusicianRepo musicianRepo;
	
	public List<Musician>allMusicians(){
		return musicianRepo.findAll();
	}
	
	public Musician createMusician(Musician musician) {
		return musicianRepo.save(musician);
	}
}
