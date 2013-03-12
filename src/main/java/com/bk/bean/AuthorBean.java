package com.bk.bean;

import com.bk.model.Author;
import com.bk.repository.AuthorRepository;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Andrei Petraru
 */
@Component
@Scope("session")
public class AuthorBean implements Serializable {

	@Autowired
	AuthorRepository authorRepository;

	private Author author;
	private Long id;

	public void init() {
		if (id == null) {
			String message = "Bad request. Please use a link from within the system.";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
			return;
		}

		author = authorRepository.findById(id);

		if (author == null) {
			String message = "Bad request. Unknown author.";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
		}
	}

	public void save() {
		authorRepository.save(author);
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
