package com.bk.bean;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bk.lazydatamodel.BookSearchLazyDataModel;
import com.bk.model.Book;
import com.bk.service.BookService;

/**
 * @author Andrei Petraru
 * Mar 23, 2013
 */

@Component
@Scope("request")
public class SearchBean {
	private static final int FIRST_RESULT = 0;
	private static final int RESULTS_PER_PAGE = 10;

	@Autowired
	private BookService bookService;

	@Autowired
	private BookSearchLazyDataModel lazyModel;

	@Autowired
	private RatingsBean ratings;

	public List<Book> search(String input) {
		lazyModel.setSearchTerm(input);
		return bookService.search(input, FIRST_RESULT, RESULTS_PER_PAGE).getResults(); 
	}

	public BookSearchLazyDataModel getLazyModel() {
		return lazyModel;
	}

	public RatingsBean getRatings() {
		return ratings;
	}

}
