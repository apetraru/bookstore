package com.bk.bean;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bk.lazydatamodel.GenreLazyDataModel;
import com.bk.model.Book;
import com.bk.service.BookService;

/**
 * @author Andrei Petraru 
 * 15 Mar 2013
 */

@Component
@Scope("request")
public class HomeBean {
	@Autowired
	private BookService bookService;
	@Autowired
	private GenreLazyDataModel lazyModel;
	@Autowired
	private RatingsBean ratings;

	private List<Book> books;

	@PostConstruct
	public void init() {
		books = bookService.findRandom();
	}

	public List<Book> getBooks() {
		return books;
	}

	public GenreLazyDataModel getLazyModel() {
		return lazyModel;
	}

	public RatingsBean getRatings() {
		return ratings;
	}

}
