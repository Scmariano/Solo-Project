package com.stephen.soloproject.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stephen.soloproject.models.Set;
import com.stephen.soloproject.repositories.SetRepo;

@Service
public class SetServ {
	@Autowired SetRepo setRepo;
	
	public List<Set>allEvents(){
		return setRepo.findAll();
	}
	
	public Set createSet(Set set) {
		return setRepo.save(set);
	}
	
	public Set findSetId(Long id) {
		Optional<Set>optSet = setRepo.findById(id);
		if(optSet.isPresent()) {
			return optSet.get();
		}else {
			return null;
		}
	}
	
	public Set updateSet(Set set) {
		return setRepo.save(set);
	}
	
	public void deleteSet(Long id) {
		setRepo.deleteById(id);
	} 

}
