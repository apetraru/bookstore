package com.bk.bean;

import static com.bk.util.Message.globalError;
import static com.bk.util.Message.msg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bk.lazydatamodel.BookGenreLazyDataModel;
import com.bk.model.Genre;
import com.bk.repository.GenreRepository;

/**
 * @author Andrei Petraru
 * 21 Mar 2013
 */

@Component
@Scope("request")
public class GenreBean {
	@Autowired private BookGenreLazyDataModel lazyModel;
	@Autowired private GenreRepository genreRepository;
	@Autowired private RatingsBean ratings;

	private Long id;
	private Genre genre;

	public void init() {
		if (id == null) {
			globalError(msg("badRequest"));
			return;
		}

		genre = genreRepository.findById(id);
		if (genre == null) {
			globalError(msg("unknownGenre"));
			return;
		}
		
		lazyModel.setGenre(genre);
	}

	public BookGenreLazyDataModel getLazyModel() {
		return lazyModel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Genre getGenre() {
		return genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	public RatingsBean getRatings() {
		return ratings;
	}
}
