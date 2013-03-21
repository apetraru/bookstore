package com.bk.bean;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bk.lazydatamodel.BookGenreLazyDataModel;
import com.bk.model.Genre;

/**
 * @author Andrei Petraru
 * 21 Mar 2013
 */

@Component
@Scope("request")
public class GenreBean {
	@Autowired
	private BookGenreLazyDataModel lazyModel;
	
	private Genre genre;
	
	@PostConstruct
	public void init() {
		genre.getBooks();
		lazyModel.setGenreId(genre.getId());
	}

	public BookGenreLazyDataModel getLazyModel() {
		return lazyModel;
	}

}
