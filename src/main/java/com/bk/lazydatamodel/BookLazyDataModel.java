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
import com.bk.repository.BookRepository;

/**
 * @author Andrei Petaru
 * 20 Mar 2013
 */
@Component
public class BookLazyDataModel extends LazyDataModel<Book> {
	@Autowired
	private BookRepository bookRepositoryr;

	@Override
	public List<Book> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
		Pageable pageable = new PageRequest(first/pageSize, pageSize);
		Page<Book> datasource = bookRepositoryr.findAll(pageable);

		setRowCount(datasource.getTotalPages());

		return datasource.getContent();
	}

}
