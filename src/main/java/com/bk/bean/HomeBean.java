package com.bk.bean;

import com.bk.lazydatamodel.GenreLazyDataModel;
import com.bk.model.Book;
import com.bk.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author Andrei Petraru
 * 15 Mar 2013
 */

@Component
@Scope("request")
public class HomeBean {

	private static final int DISPLAYED_BOOKS = 15;

	@Autowired
	private BookService bookService;

	@Autowired
	private GenreLazyDataModel lazyModel;
	
	@Autowired
	private RatingsBean ratings;

	private List<Book> books = new ArrayList<>();

	@PostConstruct
	public void init() {
		Set<Book> noDuplicates = new HashSet<>();
		Random random = new Random();
		int count = bookService.count().intValue();

		if (count < DISPLAYED_BOOKS) {
			return;
		}

		for (int i = 0; i < DISPLAYED_BOOKS; i++) {
			Book book = bookService.findById((long) random.nextInt(count));
			noDuplicates.add(book);
		}
		books.addAll(noDuplicates);
	}

	public List<Book> getBooks() {
		return Collections.unmodifiableList(books);
	}

	public GenreLazyDataModel getLazyModel() {
		return lazyModel;
	}
	
	public RatingsBean getRatings() {
		return ratings;
	}

}
