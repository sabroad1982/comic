package com.scottbroadbent.comics.repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.scottbroadbent.comics.models.Collector;

@Repository

public interface CollectorRepository extends CrudRepository<Collector, Long> {
	List<Collector> findAll();
    Collector findByEmail(String email);
}
