package com.bk.bean;

import com.bk.model.Book;
import com.bk.model.Customer;
import com.bk.repository.ReviewRepository;
import com.bk.service.BookService;
import com.bk.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.bk.util.Message.globalError;
import static com.bk.util.Message.msg;

/**
 * Created by ph on 5/17/15.
 */

@Component
@Scope("view")
public class ProfileBean {
	@Autowired private CustomerService customerService;
	@Autowired private ReviewRepository reviewRepository;
	@Autowired private BookService bookService;

	private Long id;
	private Customer customer;
	private List<Book> books;
	private Long averageRating;
	private Long booksRead;

	public void init() {
		if (id == null) {
			globalError(msg("badRequest"));
			return;
		}

		customer = customerService.findById(id);

		if (customer == null) {
			globalError(msg("unknownUser"));
			return;
		}

		books = bookService.findByCustomerId(customer.getId());
		averageRating = reviewRepository.getAverageRatingForCustomer(customer.getId());
		booksRead = reviewRepository.getNumberOfCustomerRatings(customer);
	}

	public List<Book> getBooks() {
		return books;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBooksRead() {
		return booksRead;
	}

	public Long getAverageRating() {
		return averageRating;
	}
}
