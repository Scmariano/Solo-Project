package com.stephen.soloproject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stephen.soloproject.models.Musician;

@Repository
public interface MusicianRepo extends CrudRepository <Musician,Long> {
	public List<Musician>findAll();
	public List<Musician>findBySetId(Long id);
}
