package com.bk.bean;

import com.bk.model.Book;
import com.bk.model.Customer;
import com.bk.model.Review;
import com.bk.service.BookService;
import com.bk.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.bk.util.Message.globalError;
import static com.bk.util.Message.msg;

/**
 * Created by ph on 5/17/15.
 */

@Component
@Scope("request")
public class RecommendationBean implements Serializable {
	@Autowired private SessionBean sessionBean;
	@Autowired private BookService bookService;
	@Autowired private ReviewService reviewService;

	List<Book> books = new ArrayList<>();
	List<Review> reviews = new ArrayList<>();

	@PostConstruct
	public void init() {
		Customer customer = sessionBean.getLoggedInUser();
		if (customer == null) {
			globalError(msg("loginForRecommendations"));
			return;
		}
		reviews = reviewService.getCustomerRatings(sessionBean.getLoggedInUser());
		if (reviews.isEmpty()) {
			globalError(msg("noBooksRated"));
			return;
		}

		try {
			RestTemplate restTemplate = new RestTemplate();
			restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
			Long[] result = restTemplate.getForObject("http://localhost:9000/user/" + customer.getId(), Long[].class);
			books = bookService.findByIDs(Arrays.asList(result));
		}
		catch (Exception e) {
			Logger.getLogger(RecommendationBean.class.getName())
					.log(Level.SEVERE, "Error calling book engine", "Error calling book engine");
		}
	}

	public List<Book> getBooks() {
		return books;
	}
}
