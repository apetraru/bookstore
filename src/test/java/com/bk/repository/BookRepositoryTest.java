package com.bk.repository;

import com.bk.common.AbstractIntegrationTest;
import com.bk.model.Book;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author: Andrei Petraru
 * Date: 4/27/13
 */
public class BookRepositoryTest extends AbstractIntegrationTest {
    @Autowired
    private BookRepository repository;

    private static final String ISBN = "9780439064866";
    private static final Long ID = 1L;
    private static final Long COUNT = 1L;

    @Before
    public void setUp() {
    	reset();
        Book book = new Book();
        book.setIsbn(ISBN);
        book.setTitle("title");
        book.setPages(25);
        repository.save(book);
    }

    @Test
    public void findByIdTest() {
        Book book = repository.findById(ID);
        assertEquals(book.getId(), ID);
    }

    @Test
    public void findByIsbnTest() {
        Book book = repository.findByIsbn(ISBN);
        assertEquals(book.getIsbn(), ISBN);
    }

    @Test
    public void saveTest() {
        Book book = new Book();
        book.setTitle("title");
        book.setIsbn("isbn");
        book.setPages(50);
        book = repository.save(book);
        assertNotNull(book);
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    public void duplicateIsbnSaveTestFail() {
        Book book = new Book();
        book.setTitle("title");
        book.setIsbn(ISBN);
        book.setPages(50);
        book = repository.save(book);
    }

    @Test
    public void countTest() {
        Long bookCount = repository.count();
        assertEquals(bookCount, COUNT);
    }
}
