package com.bk.service;

import com.bk.model.Book;
import com.bk.model.Customer;
import com.bk.model.Review;
import com.bk.repository.ReviewRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReviewService {

	@Autowired private ReviewRepository reviewRepository;
	@Autowired private DataSource dataSource;

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

	public List<Pair<Long, Integer>> getCustomerRatingsDTO(List<Long> bookIds, Long customerId) {
		NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("bookIds", bookIds);
		params.addValue("customerId", customerId);
		String query = "select rating, book_id from REVIEW where customer_id = :customerId and book_id in (:bookIds)";
		List<Map<String, Object>> result = jdbcTemplate.queryForList(query, params);
		List<Pair<Long, Integer>> res = new ArrayList<>();
		for (Map<String, Object> r : result) {
			Long id = (Long) r.get("book_id");
			Integer rating = (Integer) r.get("rating");
			res.add(new ImmutablePair<>(id, rating));
		}
		return res;
	}

}
