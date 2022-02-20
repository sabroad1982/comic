package com.scottbroadbent.comics.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.scottbroadbent.comics.models.Deal;

@Repository

public interface DealRepository extends CrudRepository<Deal, Long> {
	List<Deal> findAll();
}
