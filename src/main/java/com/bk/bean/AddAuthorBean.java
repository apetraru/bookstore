package com.bk.bean;

import com.bk.enums.Gender;
import com.bk.model.Author;
import com.bk.repository.AuthorRepository;
import com.bk.util.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;

/**
 * @author: Andrei Petraru
 * Date: 9/25/13
 */

@Component
@Scope("request")
public class AddAuthorBean {

	@Autowired
	private AuthorRepository authorRepository;

	private Author author;

	@PostConstruct
	public void init() {
		reset();
	}

	public void save() {
		Author savedAuthor = authorRepository.findByName(author.getName());

		if (savedAuthor == null) {
			authorRepository.save(author);
			Message.addMessage("addAuthorFormId:addAuthorButtonId", "Author saved successfully", FacesMessage.SEVERITY_INFO);
			reset();
		}
		else {
			Message.addMessage("addAuthorFormId:addAuthorButtonId", "Author already exists", FacesMessage.SEVERITY_ERROR);
		}
	}

	public void reset() {
		author = new Author();
	}

	public Gender[] getGenders() {
		return Gender.values();
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
		author.setGender(Gender.MALE);
	}
}
