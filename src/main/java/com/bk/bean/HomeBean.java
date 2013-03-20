package com.bk.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.math.random.RandomData;
import org.apache.commons.math.random.RandomDataImpl;
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
public class HomeBean implements Serializable {

	private static final Long DISPLAYED_BOOKS = 5L;

	@Autowired
	private BookService bookService;

	@Autowired
	private GenreLazyDataModel lazyModel;

	private List<Book> books = new ArrayList<>();

	@PostConstruct
	public void init() {
		RandomData random = new RandomDataImpl();
		Long count = bookService.count();

		if (DISPLAYED_BOOKS.compareTo(count) > 0) {
			return;
		}

		for (int i = 0; i < DISPLAYED_BOOKS; i++) {
			Book book = bookService.findById(random.nextLong(1, count));
			books.add(book);
		}
	}

	public List<Book> getBooks() {
		return Collections.unmodifiableList(books);
	}

	public GenreLazyDataModel getLazyModel() {
		return lazyModel;
	}

}
