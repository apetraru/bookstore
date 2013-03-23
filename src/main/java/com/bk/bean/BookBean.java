package com.bk.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.RateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bk.model.Book;
import com.bk.model.Review;
import com.bk.repository.ReviewRepository;
import com.bk.service.BookService;

/**
 * @author Andrei Petraru
 * 4 Mar 2013
 */
@Component
@Scope("session")
public class BookBean implements Serializable {

	@Autowired
	private LoginBean loginBean;

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private BookService bookService;

	private Book book;
	private Review bookReview;
	private Long id;

	public void init() {
		if (id == null) {
			String message = "Bad request. Please use a link from within the system.";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
			return;
		}

		book = bookService.findById(id);

		if (book == null) {
			String message = "Bad request. Unknown book.";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
			return;
		}

		if (loginBean.getLoggedInUser() != null) {
			bookReview = reviewRepository.getCustomerRating(book, loginBean.getLoggedInUser());
			if (bookReview == null) {
				newRating();
			}
		}
		else {
			newRating();
		}
	}

	public String getAverageRating() {
		Double averageRating = reviewRepository.getBookRating(book);
		if (averageRating == null) {
			return "0.00";
		}
		return String.format("%1$,.2f", averageRating);
	}

	public Long getNumberOfRatings() {
		return reviewRepository.getNumberOfBookRatings(book);
	}

	public void addRating(RateEvent event) {
		Integer rate = (Integer) event.getRating();
		if (bookReview.getBook() == null) {
			bookReview.setBook(book);
			bookReview.setCustomer(loginBean.getLoggedInUser());
		}
		bookReview.setRating(rate);
		reviewRepository.save(bookReview);
	}

	public void removeRating() {
		bookReview.setRating(null);
		reviewRepository.save(bookReview);
	}

	private void newRating() {
		bookReview = new Review();
		bookReview.setRating(0);
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Review getBookRating() {
		return bookReview;
	}

	public void setBookRating(Review bookRating) {
		this.bookReview = bookRating;
	}
}
