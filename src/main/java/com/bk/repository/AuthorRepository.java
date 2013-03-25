package com.bk.repository;

import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bk.model.Author;

/**
 * @author Andrei Petraru
 * Date: 2/2/13
 */
@Transactional(readOnly = true)
public interface AuthorRepository extends Repository<Author, Long> {

    @Transactional
    Author save(Author author);

    Author findByName(String name);

	Author findById(Long id);
}
