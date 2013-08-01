package com.bk.service

import spock.lang.Specification

import com.bk.model.Book
import com.bk.repository.BookRepository
import com.bk.service.impl.BookServiceImpl

/**
 * @author Andrei Petraru
 * 14 Jun 2013
 */
class BookServiceTest extends Specification {
	BookRepository repository = Mock()
	BookService service = new BookServiceImpl()

	def setup() {
		service.@repository = repository
	}

	def 'test save'() {
		setup:
		Book book = new Book(title: 'title', isbn: 'isbn', pages: 5)

		when:
		service.save(book)

		then:
		1 * repository.save(book)
	}

	def 'test find by ID'() {
		when:
		service.findById(1L)

		then:
		1 * repository.findById(1L)
	}
	
	def 'count'() {
		when:
		service.count()
		
		then:
		1 * repository.count()
	}
}
