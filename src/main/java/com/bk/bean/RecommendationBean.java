package com.bk.bean;

import com.bk.model.Book;
import com.bk.model.Customer;
import com.bk.model.Review;
import com.bk.repository.ReviewRepository;
import com.bk.service.BookService;
import com.bk.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.bk.util.Message.*;

/**
 * Created by ph on 5/17/15.
 */

@Component
@Scope("request")
public class RecommendationBean implements Serializable {
	@Autowired
	private SessionBean sessionBean;

	@Autowired
	private BookService bookService;

	@Autowired
	private ReviewRepository reviewRepository;

	List<Book> books = new ArrayList<>();
	List<Review> reviews = new ArrayList<>();

	@PostConstruct
	public void init() {
		Customer customer = sessionBean.getLoggedInUser();
		if (customer == null) {
			globalError(msg("loginForRecommendations"));
			return;
		}
		reviews = reviewRepository.getCustomerRatings(sessionBean.getLoggedInUser());
		if (reviews.isEmpty()) {
			globalError(msg("noBooksRated"));
			return;
		}
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		Long[] result = restTemplate.getForObject("http://localhost:9000/user/" + customer.getId(), Long[].class);
		books = bookService.findByIDs(Arrays.asList(result));
//		books = reviews
//				.stream()
//				.map(review -> review.getBook())
//				.collect(Collectors.toList());
	}

	public List<Book> getBooks() {
		return books;
	}
}
