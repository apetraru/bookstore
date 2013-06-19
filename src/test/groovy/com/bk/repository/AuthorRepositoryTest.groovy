package com.bk.repository

import org.springframework.beans.factory.annotation.Autowired

import com.bk.common.BaseGroovyTest
import com.bk.model.Author

/**
 * @author Andrei Petraru
 * 5 Jun 2013
 */
class AuthorRepositoryTest extends BaseGroovyTest {
	@Autowired
	AuthorRepository repository

	def setup() {
		Author author = new Author(name: 'author')
		repository.save(author)
	}

	def 'save author'() {
		when:
		Author author = new Author(name: 'author')

		then:
		repository.save(author)
	}

	def 'find by name'() {
		when:
		Author author = repository.findByName('author')

		then:
		author.name == 'author'
	}

	def 'find by non existing name'() {
		expect:
		repository.findByName('no author') == null
	}

	def 'find by ID'() {
		when:
		Author author = repository.findById(1L)

		then:
		author.id == 1L
	}

	def 'find by non existing ID'() {
		expect:
		repository.findById(2L) == null
	}
}