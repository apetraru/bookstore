package com.bk.repository

import com.bk.common.BaseGroovyTest
import com.bk.model.Author
import com.bk.model.Book

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

class BookRepositoryTest extends BaseGroovyTest {

	@Autowired
	BookRepository repository
	
	Author author = new Author(name: 'author')
	Author authorWithNoBooks = new Author(name: 'author2')
	
	Pageable page = new PageRequest(0, 5)

	def setup() {
		entityManager.persist(author)
		entityManager.persist(authorWithNoBooks)
		Book book = new Book(title: 'title', isbn: 'isbn', pages: 5, author: author)
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
	
	def 'find by book with pagination'() {
		when:
		Page books = repository.findByAuthor(author, page)

		then:
		books.hasContent()
	}

	def 'find by author with no books with pagination'() {
		when:
		Page books = repository.findByAuthor(authorWithNoBooks, page)

		then:
		!books.hasContent()
	}
	
	def 'find by null author with pagination'() {
		when:
		Page books = repository.findByAuthor(null, page)

		then:
		!books.hasContent()
	}
}
