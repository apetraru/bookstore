package com.bk.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bk.model.Book;
import com.bk.service.ReviewService;

/**
 * @author Andrei Petraru
 * Mar 23, 2013
 */

@Component
public class RatingsBean {
	@Autowired
	private ReviewService reviewService;

	public String getAverageRating(Book book) {
		Double averageRating = reviewService.getBookRating(book);
		if (averageRating == null) {
			return "0.00";
		}
		return String.format("%1$,.2f", averageRating);
	}

	public Long getNumberOfRatings(Book book) {
		return reviewService.getNumberOfBookRatings(book);
	}

}
