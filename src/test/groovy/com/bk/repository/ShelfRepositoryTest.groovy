package com.bk.repository

import com.bk.common.BaseGroovyTest
import com.bk.model.Book
import com.bk.model.Customer
import com.bk.model.Shelf
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author: Andrei Petraru
 * Date: 9/22/13
 */
class ShelfRepositoryTest extends BaseGroovyTest {
	@Autowired
	ShelfRepository shelfRepository

	@Autowired
	BookRepository bookRepository

	@Autowired
	CustomerRepository customerRepository

	def 'save'() {
		when:
		Shelf shelf = new Shelf()

		then:
		shelfRepository.save(shelf) != null
	}

	def 'find by book and customer'() {
		setup:
		Customer customer = new Customer(username: 'new_user', password: 'pass', emailAddress: 'new_email@email.com')
		Book book = new Book(title: 'title', isbn: 'new_isbn', pages: 5)
		Shelf shelf = new Shelf(customer: customer, book: book)
		bookRepository.save(book)
		customerRepository.save(customer)
		shelfRepository.save(shelf)

		expect:
		shelfRepository.getCustomerShelf(book, customer) != null
	}


}
