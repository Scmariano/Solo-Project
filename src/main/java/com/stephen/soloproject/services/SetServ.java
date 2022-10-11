package com.stephen.soloproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stephen.soloproject.models.Set;
import com.stephen.soloproject.models.User;
import com.stephen.soloproject.repositories.SetRepo;

@Service
public class SetServ {
	@Autowired SetRepo setRepo;
	
	// show all events
	public List<Set>allEvents(){
		return setRepo.findAll();
	}
	
	//Create
	public Set createSet(Set set) {
		return setRepo.save(set);
	}
	
	// Get a Set 
	public Set findSetId(Long id) {
		Optional<Set>optSet = setRepo.findById(id);
		if(optSet.isPresent()) {
			return optSet.get();
		}else {
			return null;
		}
	}
	
	// Update
	public Set updateSet(Set set) {
		return setRepo.save(set);
	}
	
	//Delete Event('Set')
	public void deleteSet(Long id) {
		setRepo.deleteById(id);
	} 
	
	// Get the Liker
	public Set findLikerId(Long likerId) {
		Optional<Set>optSetLikers = setRepo.findLikersById(likerId);
		if(optSetLikers.isPresent()) {
			return optSetLikers.get();
		}else {
			return null;
		}
	}
	
	

	//like
	public void likeSet(Set set, User userId) {
		List<User> likers = set.getLikers();
		likers.add(userId);
		setRepo.save(set);
	}
	
	//unlike
	public void unLikeSet(Set set, User userId) {
		List<User> likers = set.getLikers();
		likers.remove(userId);
		setRepo.save(set);
	}

	
	
}
