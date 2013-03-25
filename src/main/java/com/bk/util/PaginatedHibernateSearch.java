package com.bk.util;

import java.util.Collections;
import java.util.List;

/**
 * @author Andrei Petraru
 * Mar 24, 2013
 */
public class PaginatedHibernateSearch<T> {
	private List<T> results;

	private int resultsSize;

	public List<T> getResults() {
		return Collections.unmodifiableList(results);
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public int getResultsSize() {
		return resultsSize;
	}

	public void setResultsSize(int resultsSize) {
		this.resultsSize = resultsSize;
	}

}
