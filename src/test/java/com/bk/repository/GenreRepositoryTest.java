package com.bk.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.bk.common.AbstractIntegrationTest;
import com.bk.model.Genre;

/**
 * @author: Andrei Petraru
 * Date: 4/28/13
 */
public class GenreRepositoryTest extends AbstractIntegrationTest {

    private static final String GENRE = "genre";
    private static final String NEW_GENRE = "new_genre";

    @Autowired
    private GenreRepository repository;

    @Before
    public void setUp() {
    	reset();
        Genre genre = new Genre();
        genre.setGenre(GENRE);
        repository.save(genre);
    }

    @Test
    public void findByIdTest() {
        Genre genre = repository.findById(1L);
        assertNotNull(genre);
    }

    @Test
    public void findAllTest() {
        Pageable pageable = new PageRequest(10, 1);
        Page<Genre> genres = repository.findAll(pageable);
        assertEquals(genres.getTotalElements(), 1L);
    }

    @Test
    public void testSaveOk() {
        Genre genre = new Genre();
        genre.setGenre(NEW_GENRE);
        genre = repository.save(genre);
        assertNotNull(genre);
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void duplicateSaveTestFail() {
        Genre genre = new Genre();
        genre.setGenre(GENRE);
        repository.save(genre);
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void emptyGenreSaveTestFail() {
        Genre genre = new Genre();
        repository.save(genre);
    }
}
