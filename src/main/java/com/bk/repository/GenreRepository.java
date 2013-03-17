package com.bk.repository;

import java.util.List;

import org.springframework.data.repository.Repository;

import com.bk.model.Genre;

/**
 * @author Andrei Petraru
 * Mar 16, 2013
 */
public interface GenreRepository extends Repository<Genre, Long> {
	List<Genre> findAll();

	Long count();

}
