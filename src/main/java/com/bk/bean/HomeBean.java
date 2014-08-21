package com.bk.bean;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

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

	private static final int DISPLAYED_BOOKS = 15;

	@Autowired private BookService bookService;
	@Autowired private GenreLazyDataModel lazyModel;
	@Autowired private RatingsBean ratings;

	private Set<Book> books = new HashSet<>();

	@PostConstruct
	public void init() {
//		Random random = new Random();
//		int count = bookService.count().intValue();
//
//		if (count < DISPLAYED_BOOKS) {
//			return;
//		}
//		while (books.size() < DISPLAYED_BOOKS) {
//			books.add(bookService.findById((long) random.nextInt(count)));
//		}
	}

	public Set<Book> getBooks() {
		return Collections.unmodifiableSet(books);
	}

	public GenreLazyDataModel getLazyModel() {
		return lazyModel;
	}

	public RatingsBean getRatings() {
		return ratings;
	}

}
