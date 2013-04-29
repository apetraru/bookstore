package com.bk.repository;

import com.bk.common.AbstractIntegrationTest;
import com.bk.model.Author;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * @author: Andrei Petraru
 * Date: 4/27/13
 */
public class AuthorRepositoryTest extends AbstractIntegrationTest {

    @Autowired
    private AuthorRepository repository;

    private static final String AUTHOR_NAME = "VASILE";
    private static final Long ID = 1L;

    @Before
    public void setUp() {
    	reset();
        Author author = new Author();
        author.setName(AUTHOR_NAME);
        repository.save(author);
    }

    @Test
    public void findByNameTest() {
        Author author = repository.findByName(AUTHOR_NAME);
        assertEquals(author.getName(), AUTHOR_NAME);
    }

    @Test
    public void findByAuthorId() {
        Author author = repository.findById(ID);
        assertEquals(author.getId(), ID);
    }

    @Test
    public void saveTest() {
        Author newAuthor = new Author();
        newAuthor = repository.save(newAuthor);
        assertEquals(newAuthor.getId(), (Long) 2L);
    }

}
