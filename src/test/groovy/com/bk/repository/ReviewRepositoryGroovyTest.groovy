package com.bk.repository

import com.bk.common.BaseGroovyTest
import com.bk.model.Book
import com.bk.model.Customer
import com.bk.model.Review
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author: Andrei Petraru
 * Date: 6/5/13
 */
class ReviewRepositoryGroovyTest extends BaseGroovyTest {
	@Autowired
	ReviewRepository repository

	def setup() {
		Customer customer = new Customer(username: 'user', password: 'pass', emailAddress: 'email@email.com')
		Book book = new Book(title: 'title', isbn: 'isbn', pages: 5)
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
}
