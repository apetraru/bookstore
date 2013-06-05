package com.bk.repository

import com.bk.common.BaseGroovyTest
import com.bk.model.Genre
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable

/**
 * @author: Andrei Petraru
 * Date: 6/5/13
 */
class GenreRepositoryGroovyTest extends BaseGroovyTest {
	@Autowired
	GenreRepository repository

	def setup() {
		Genre genre = new Genre(genre: "genre")
		repository.save(genre)
	}

	def 'save genre'() {
		when:
		Genre genre = new Genre(genre: "new genre")
		genre = repository.save(genre)

		then:
		genre != null
	}

	def 'save genre with empty genre throws DataIntegrityViolationException'() {
		when:
		Genre genre = new Genre()
		repository.save(genre)

		then:
		thrown DataIntegrityViolationException
	}

	def 'find by ID'() {
		when:
		Genre genre = repository.findById(1L)

		then:
		genre.id == 1L
	}

	def 'find by non existent ID'() {
		expect:
		repository.findById(2L) == null
	}

	def 'find all with pagination'() {
		when:
		Pageable page = new PageRequest(10, 1)
		Page<Genre> genres = repository.findAll(page)

		then:
		genres.totalElements == 1
	}
}
