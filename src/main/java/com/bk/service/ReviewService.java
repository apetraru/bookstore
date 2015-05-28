package com.bk.service;

import com.bk.model.Book;
import com.bk.model.Customer;
import com.bk.model.Review;
import com.bk.repository.ReviewRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	public Review save(Review review) {
		return reviewRepository.save(review);
	}

	public Double getBookRating(Book book) {
		return reviewRepository.getBookRating(book);
	}

	public Review getCustomerRating(Book book, Customer customer) {
		return reviewRepository.getCustomerRating(book, customer);
	}

	public Long getNumberOfBookRatings(Book book) {
		return reviewRepository.getNumberOfBookRatings(book);
	}

	public List<Review> getCustomerRatings(Customer customer) {
		return reviewRepository.getCustomerRatings(customer);
	}

	public Page<Review> findByBook(Book book, Pageable pageable) {
		return reviewRepository.findByBook(book, pageable);
	}

	public Double getAverageRatingForCustomer(Customer customer) {
		return reviewRepository.getAverageRatingForCustomer(customer);
	}

	public Long getNumberOfCustomerRatings(Customer customer) {
		return reviewRepository.getNumberOfCustomerRatings(customer);
	}

}
