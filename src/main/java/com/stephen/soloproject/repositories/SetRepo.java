package com.stephen.soloproject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stephen.soloproject.models.Set;
import com.stephen.soloproject.models.Song;



@Repository
public interface SetRepo extends CrudRepository <Set, Long> {
	public List<Set>findAll();
	public Optional<Set>findLikersById(Long likerId);
	public Optional<Set>findSetSongsById(Long songId);
	public List<Set>findAllBySetSongs(Song song);
	public List<Set>findBySetSongsNotContains(Song song);
	public List<Set>findBySetSongsId(Long songId);
}
