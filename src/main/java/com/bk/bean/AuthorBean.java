package com.bk.bean;

import static com.bk.util.Message.globalError;
import static com.bk.util.Message.msg;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bk.lazydatamodel.BookAuthorLazyDataModel;
import com.bk.model.Author;
import com.bk.repository.AuthorRepository;

/**
 *
 * @author Andrei Petraru
 */
@Component
@Scope("request")
public class AuthorBean implements Serializable {

	@Autowired
	private AuthorRepository authorRepository;
	
	@Autowired
	private BookAuthorLazyDataModel lazyDataModel;

	private Author author;
	private Long id;

	public void init() {
		if (id == null) {
			globalError(msg("badRequest"));
			return;
		}

		author = authorRepository.findById(id);

		if (author == null) {
			globalError(msg("unknownAuthor"));
			return;
		}
		
		lazyDataModel.setAuthor(author);
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

	public BookAuthorLazyDataModel getLazyDataModel() {
		return lazyDataModel;
	}
	
}
