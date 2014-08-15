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

import com.bk.model.Author;
import com.bk.model.Book;
import com.bk.repository.BookRepository;

/**
 * @author Andrei Petraru
 * 18 Jul 2013
 */

@Component
public class BookAuthorLazyDataModel extends LazyDataModel<Book> {
	@Autowired
	private BookRepository bookRepository;

	private Author author;

	@Override
	public List<Book> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		int elementsPerPage = first / pageSize;
		Pageable pageable = new PageRequest(elementsPerPage, pageSize);

		Page<Book> datasource = bookRepository.findByAuthor(author, pageable);

		setRowCount((int) datasource.getTotalElements());

		return datasource.getContent();
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

}
