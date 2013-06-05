package com.bk.repository

import com.bk.common.BaseGroovyTest
import com.bk.model.Book
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException

class BookRepositoryGroovyTest extends BaseGroovyTest {

	@Autowired
	BookRepository repository

	def setup() {
		Book book = new Book(title: 'title', isbn: 'isbn', pages: 5)
		repository.save(book)
	}

	def 'save book'() {
		when:
		Book book = new Book(title: 'title', isbn: 'new_isbn', pages: 5)

		then:
		repository.save(book) != null
	}

	def 'save book with duplicate ISBN throws DataIntegrityViolationException'() {
		when:
		Book book = new Book(title: 'title', isbn: 'isbn', pages: 5)
		repository.save(book)

		then:
		thrown DataIntegrityViolationException
	}

	def 'save book with no ISBN throws DataIntegrityViolationException'() {
		when:
		Book book = new Book(isbn: 'new_isbn', pages: 5)
		repository.save(book)

		then:
		thrown DataIntegrityViolationException
	}

	def 'save book with no title throws DataIntegrityViolationException'() {
		when:
		Book book = new Book(isbn: 'new_isbn', pages: 5)
		repository.save(book)

		then:
		thrown DataIntegrityViolationException
	}

	def 'save book with no pages throws DataIntegrityViolationException'() {
		when:
		Book book = new Book(title: 'title', isbn: 'new_isbn')
		repository.save(book)

		then:
		thrown DataIntegrityViolationException
	}

	def 'find by ID'() {
		when:
		Book book = repository.findById(1L)

		then:
		book.id == 1L
	}

	def 'find by non existing ID'() {
		expect:
		repository.findById(2L) == null
	}
	
	def 'find by ISBN'() {
		when:
		Book book = repository.findByIsbn('isbn')
		
		then:
		book.isbn == 'isbn'
	}
	
	def 'find by non existing ISBN'() {
		expect:
		repository.findByIsbn("no isbn") == null
	}
	
	def 'count'() {
		expect:
		repository.count() == 1L
	}
}
