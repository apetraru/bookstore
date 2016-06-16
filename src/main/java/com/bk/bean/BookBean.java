package com.bk.bean;

import static com.bk.util.Message.globalError;
import static com.bk.util.Message.msg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.event.RateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.bk.enums.Status;
import com.bk.lazydatamodel.ReviewLazyDataModel;
import com.bk.model.Book;
import com.bk.model.Customer;
import com.bk.model.Review;
import com.bk.model.Shelf;
import com.bk.repository.ShelfRepository;
import com.bk.service.BookService;
import com.bk.service.ReviewService;

/**
 * @author Andrei Petraru
 * 4 Mar 2013
 */
@Component
@Scope("view")
public class BookBean {

	@Autowired private SessionBean sessionBean;
	@Autowired private ReviewService reviewService;
	@Autowired private BookService bookService;
	@Autowired private ShelfRepository shelfRepository;
	@Autowired private ReviewLazyDataModel lazyDataModel;

	private Book book;
	private Review bookReview;
	private Review likeReview;
	private Shelf shelf;
	private Long id;
	private List<Book> recommendations = new ArrayList<>();

	public void init() {
		Customer user = sessionBean.getLoggedInUser();

		if (id == null) {
			globalError(msg("badRequest"));
			return;
		}

		book = bookService.findById(id);

		if (book == null) {
			globalError(msg("unknownBook"));
			return;
		}

		if (user != null) {
			bookReview = reviewService.getCustomerRating(book, user);
			if (bookReview == null) {
				newRating();
			}
		}
		else {
			newRating();
		}

		lazyDataModel.setBook(book);

		if (bookReview.getBook() == null) {
			bookReview.setBook(book);
			bookReview.setCustomer(user);
		}

		shelf = shelfRepository.getCustomerShelf(book, user);
		if (shelf == null) {
			shelf = new Shelf();
			shelf.setBook(book);
			shelf.setCustomer(user);
		}

	}

	public String getAverageRating() {
		Double averageRating = reviewService.getBookRating(book);
		if (averageRating == null) {
			return "0.00";
		}
		return String.format("%1$,.2f", averageRating);
	}

	public Long getNumberOfRatings() {
		return reviewService.getNumberOfBookRatings(book);
	}

	public void addRating(RateEvent event) {
		Integer rate = (Integer) event.getRating();
		bookReview.setRating(rate);
		saveReview();
	}
	
	public void saveReview() {
		String comment = bookReview.getComment();
		if (!StringUtils.isEmpty(comment) && bookReview.getPublishDate() == null) {
			bookReview.setPublishDate(new Date());
		}
		shelf.setStatus(Status.READ);
		saveShelf();
		reviewService.save(bookReview);
	}

	public void like() {
		if (isReviewLiked()) {
			likeReview.removeCustomerLike(getUserId());
		}
		else {
			likeReview.addCustomerLike(getUserId());
		}
		reviewService.save(likeReview);
	}

	public boolean isReviewLiked() {
		return isLiked(likeReview);
	}

	public String isLikedButtonText(Review review) {
		return isLiked(review) ? "unlike" : "like";
	}

	public void saveShelf() {
		shelfRepository.save(shelf);
	}

	public void removeRating() {
		bookReview.setRating(null);
		reviewService.save(bookReview);
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

	public Review getBookReview() {
		return bookReview;
	}

	public void setBookReview(Review bookReview) {
		this.bookReview = bookReview;
	}

	public Status[] getStatuses() {
		return Status.values();
	}

	public ReviewLazyDataModel getLazyDataModel() {
		return lazyDataModel;
	}

	public Review getLikeReview() {
		return likeReview;
	}

	public void setLikeReview(Review likeReview) {
		this.likeReview = likeReview;
	}

	public Shelf getShelf() {
		return shelf;
	}

	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}

	public Date getToday() {
		return new Date();
	}

	public List<Book> getRecommendations() {
		return recommendations;
	}

	private void newRating() {
		bookReview = new Review();
		bookReview.setRating(0);
	}

	private Long getUserId() {
		return sessionBean.getLoggedInUser().getId();
	}

	private boolean isLoggedIn() {
		return sessionBean.isLoggedOn();
	}

	private boolean isLiked(Review review) {
		if (!isLoggedIn()) {
			return false;
		}
		
		if (review != null) {
			return review.isLikedByCustomer(getUserId());
		}
		return true;
	}

	/**
	 * Retrieves the recommendations from the recommendation server if it's up
	 * TODO: enable this when server is up
	 */
	@SuppressWarnings("unused")
	private void getRecommendationsFromURL() {
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		Long[] result = restTemplate.getForObject("http://localhost:9000/book/" + book.getId(), Long[].class);
		recommendations = bookService.findByIDs(Arrays.asList(result));
	}
}
