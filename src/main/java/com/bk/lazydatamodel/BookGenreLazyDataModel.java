package com.bk.lazydatamodel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.bk.model.Book;
import com.bk.model.Genre;
import com.bk.service.BookService;

/**
 * @author Andrei Petraru
 * 21 Mar 2013
 */

@Component
public class BookGenreLazyDataModel extends LazyDataModel<Book> {
	private static final long serialVersionUID = -8821411664798794007L;

	@Autowired private BookService bookService;

	private Genre genre;

	@Override
	public List<Book> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		int elementsPerPage = first / pageSize;
		Pageable pageable = new PageRequest(elementsPerPage, pageSize);

		Page<Book> datasource = bookService.findByGenres(genre, pageable);

		setRowCount((int) datasource.getTotalElements());

		return datasource.getContent();
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

}
