package com.bk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

import com.bk.model.Genre;

/**
 * @author Andrei Petraru
 * Mar 16, 2013
 */
public interface GenreRepository extends Repository<Genre, Long> {

	Page<Genre> findAll(Pageable pageable);

}
