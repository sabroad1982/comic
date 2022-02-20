package com.scottbroadbent.comics.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scottbroadbent.comics.models.Share;

@Repository


public interface ShareRepository extends CrudRepository<Share, Long>{
	List<Share> findAll();
}
