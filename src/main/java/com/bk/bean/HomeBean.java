package com.bk.bean;

import com.bk.model.Book;
import com.bk.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Andrei Petraru
 * 15 Mar 2013
 */

@Component
@Scope("request")
public class HomeBean {
    @Autowired private BookService bookService;

    private List<Book> books = new ArrayList<>();

    @PostConstruct
    public void init() {
        books = bookService.findRandom();
    }

    public List<Book> getBooks() {
        return books;
    }
}
