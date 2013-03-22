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
import com.bk.predicate.BookPredicate;
import com.bk.repository.BookRepository;
import com.mysema.query.types.Predicate;

/**
 * @author Andrei Petraru
 * 21 Mar 2013
 */

@Component
public class BookGenreLazyDataModel extends LazyDataModel<Book> {
	@Autowired
	private BookRepository bookRepository;

	private Genre genre;

	@Override
	public List<Book> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		Predicate predicate = BookPredicate.searchByGenre(genre);
		int elementsPerPage = first / pageSize;
		Pageable pageable = new PageRequest(elementsPerPage, pageSize);

		Page<Book> datasource = bookRepository.findAll(predicate, pageable);

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
