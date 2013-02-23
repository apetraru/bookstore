package com.bk.service;

import com.bk.model.Book;
import com.bk.predicate.BookPredicate;
import com.bk.repository.BookRepository;
import com.mysema.query.types.Predicate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * User: ph
 * Date: 1/28/13
 */

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository repository;

    @Override
    public List<Book> search(String searchTerm) {
        Predicate predicate = BookPredicate.searchTitleOrAuthor(searchTerm);

        Iterable<Book> books = repository.findAll(predicate);
        List<Book> bookList = new ArrayList<>();
        CollectionUtils.addAll(bookList, books.iterator());

        return bookList;
    }

    @Override
    public Book save(Book book) {
        return repository.save(book);
    }

    @Override
    public Book findByIsbn(String isbn) {
        return repository.findByIsbn(isbn);
    }
}
