package com.bk.repository

import com.bk.common.BaseGroovyTest
import com.bk.model.Book
import com.bk.model.Customer
import com.bk.model.Review
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

/**
 * @author: Andrei Petraru
 * Date: 6/5/13
 */
class ReviewRepositoryTest extends BaseGroovyTest {
	@Autowired
	ReviewRepository repository
	
	Customer customer = new Customer(username: 'user', password: 'pass', emailAddress: 'email@email.com')
	Book book = new Book(title: 'title', isbn: 'isbn', pages: 5)
	Book bookNoReviews = new Book(title: 'title2', isbn: 'isbn2', pages: 5)
	
	Pageable page = new PageRequest(0, 5)

	def setup() {
		entityManager.persist(customer)
		entityManager.persist(book)
		entityManager.persist(bookNoReviews)
		Review review = new Review(book: book, customer: customer, rating: 5)
		repository.save(review)
	}

	def 'save review'() {
		when:
		Review review = new Review()
		review = repository.save(review)

		then:
		review != null
	}
	
	def 'get book rating'() {
		expect:
		repository.getBookRating(book) == 5
	}
	
	def 'get book rating for null book'() {
		expect:
		repository.getBookRating(null) == null
	}
	
	def 'get customer rating'() {
		expect:
		repository.getCustomerRating(book, customer).id == 1L
	}
	
	def 'get customer rating for null book'() {
		expect:
		repository.getCustomerRating(book, null) == null
	}
	
	def 'get customer rating for null customer'() {
		expect:
		repository.getCustomerRating(null, customer) == null
	}
	
	def 'get customer rating for null customer and null book'() {
		expect:
		repository.getCustomerRating(null, null) == null
	}
	
	def 'get number of ratings'() {
		expect: 
		repository.getNumberOfBookRatings(book) == 1L
	}
	
	def 'get number of ratings with 0 rating book'() {
		when:
		Review review = new Review()
		repository.save(review)
		
		then:
		repository.getNumberOfBookRatings(book) == 1L
	}

	def 'find by book with pagination'() {
		when:
		
		Page reviews = repository.findByBook(book, page)

		then:
		reviews.hasContent()
	}

	def 'find by book with no reviews with pagination'() {
		when:
		Page reviews = repository.findByBook(bookNoReviews, page)

		then:
		!reviews.hasContent()
	}
	
	def 'find by null book with pagination'() {
		when:
		Page reviews = repository.findByBook(null, page)

		then:
		!reviews.hasContent()
	}
	
}
