package com.bk.repository

import org.springframework.beans.factory.annotation.Autowired

import com.bk.common.BaseGroovyTest
import com.bk.model.Book
import com.bk.model.Customer
import com.bk.model.Review

/**
 * @author: Andrei Petraru
 * Date: 6/5/13
 */
class ReviewRepositoryTest extends BaseGroovyTest {
	@Autowired
	ReviewRepository repository
	Customer customer = new Customer(username: 'user', password: 'pass', emailAddress: 'email@email.com')
	Book book = new Book(title: 'title', isbn: 'isbn', pages: 5)

	def setup() {
		entityManager.persist(customer)
		entityManager.persist(book)
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
	
}
