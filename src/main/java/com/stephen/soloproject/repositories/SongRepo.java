package com.stephen.soloproject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stephen.soloproject.models.Song;

@Repository
public interface SongRepo extends CrudRepository<Song, Long> {
	public List<Song>findAll();
}
