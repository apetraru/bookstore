package com.bk.lazydatamodel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.bk.model.Book;
import com.bk.model.Review;
import com.bk.service.ReviewService;

/**
 * @author Andrei Petraru
 * 10 Jul 2013
 */

@Component
public class ReviewLazyDataModel extends LazyDataModel<Review> {
	private static final long serialVersionUID = -370455618693353963L;

	@Autowired private ReviewService reviewService;

	private Book book;

	@Override
	public List<Review> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		int elementsPerPage = first / pageSize;
		Pageable pageable = new PageRequest(elementsPerPage, pageSize, Sort.Direction.DESC, "likes");
		Page<Review> datasource = reviewService.findByBook(book, pageable);
		setRowCount((int) datasource.getTotalElements());
		return datasource.getContent();
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
