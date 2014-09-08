package com.bk.bean.admin;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bk.enums.Gender;
import com.bk.model.Author;
import com.bk.repository.AuthorRepository;
import static com.bk.util.Message.*;

/**
 * @author: Andrei Petraru
 * Date: 9/25/13
 */

@Component
@Scope("request")
public class AddAuthorBean {
	
	private static final String ADD_AUTHOR = "addAuthorForm:addAuthor";

	@Autowired private AuthorRepository authorRepository;

	private Author author;

	@PostConstruct
	public void init() {
		reset();
	}

	public void save() {
		Author savedAuthor = authorRepository.findByName(author.getName());

		if (savedAuthor == null) {
			authorRepository.save(author);
			reset();
			info(ADD_AUTHOR, msg("authorSavingSuccess"));
		}
		else {
			error("addAuthorForm:name", msg("authorAlreadyExists"));
		}
	}

	public void reset() {
		author = new Author();
		author.setGender(Gender.MALE);
	}

	public Gender[] getGenders() {
		return Gender.values();
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
}
