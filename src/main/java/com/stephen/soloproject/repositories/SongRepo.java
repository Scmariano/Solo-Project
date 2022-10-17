package com.stephen.soloproject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stephen.soloproject.models.Set;
import com.stephen.soloproject.models.Song;
import com.stephen.soloproject.models.User;

@Repository
public interface SongRepo extends CrudRepository<Song, Long> {
	public List<Song>findAll();
	public List<Song>findBySongsSetId(Long id);
	public List<Song>findAllBySongsSet(Set set);
	public List<Song>findBySongsSetNotContains(Set Set);
	public List<Song>findAllByCreator(User user);
	public List<Song>findByCreatorNotContains(User user);
}
