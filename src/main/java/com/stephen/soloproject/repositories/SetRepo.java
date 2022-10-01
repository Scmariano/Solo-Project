package com.stephen.soloproject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stephen.soloproject.models.Set;

@Repository
public interface SetRepo extends CrudRepository <Set, Long> {
	public List<Set>findAll();
}
