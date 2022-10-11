package com.stephen.soloproject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stephen.soloproject.models.Set;
import com.stephen.soloproject.models.Song;
import com.stephen.soloproject.models.User;


@Repository
public interface SetRepo extends CrudRepository <Set, Long> {
	public List<Set>findAll();
	public Optional<Set>findLikersById(Long likerId);
	public List<Set>findByUserNotContains(User user);
	public List<Set>findAllBySongs(Song song);
}
