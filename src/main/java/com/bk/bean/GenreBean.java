package com.bk.bean;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bk.lazydatamodel.BookGenreLazyDataModel;

/**
 * @author Andrei Petraru
 * 21 Mar 2013
 */

@Component
@Scope("request")
public class GenreBean {
	@Autowired
	private BookGenreLazyDataModel lazyModel;

	private Long id;

	public void init() {
		if (id == null) {
			String message = "Bad request. Please use a link from within the system.";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
			return;
		}

		lazyModel.setGenreId(id);

		if (lazyModel == null) {
			String message = "Bad request. Unknown book.";
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
			return;
		}
	}

	public BookGenreLazyDataModel getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(BookGenreLazyDataModel lazyModel) {
		this.lazyModel = lazyModel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
