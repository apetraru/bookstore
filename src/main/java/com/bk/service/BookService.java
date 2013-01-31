package com.bk.service;

import com.bk.model.Book;
import java.util.List;

/**
 * User: ph
 * Date: 1/28/13
 */
public interface BookService {
    List<Book> search(String searchTerm, int page, int index);
    Book save(Book book);
    Book findByIsbn(String isbn);
}
