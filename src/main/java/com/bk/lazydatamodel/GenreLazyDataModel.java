package com.bk.lazydatamodel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.bk.model.Genre;
import com.bk.repository.GenreRepository;

/**
 * @author Andrei Petraru
 * 20 Mar 2013
 */
@Component
public class GenreLazyDataModel extends LazyDataModel<Genre> {
	private static final long serialVersionUID = -7009897375577439822L;
	@Autowired private GenreRepository genreRepository;

	@Override
	public List<Genre> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
		int elementsPerPage = first / pageSize;
		Pageable pageable = new PageRequest(elementsPerPage, pageSize);
		Page<Genre> datasource = genreRepository.findAll(pageable);

		setRowCount((int) datasource.getTotalElements());

		return datasource.getContent();
	}

}
