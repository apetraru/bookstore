package com.bk.model;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.StandardFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.apache.solr.analysis.StopFilterFactory;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

import com.bk.enums.Language;

/**
 * @author Andrei Petraru
 */

@Entity
@Table(name = "BOOK")
@AnalyzerDef(name = "customanalyzer", 
			tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class), filters = {
				@TokenFilterDef(factory = StandardFilterFactory.class),
				@TokenFilterDef(factory = LowerCaseFilterFactory.class), 
				@TokenFilterDef(factory = StopFilterFactory.class) })
@Indexed
public class Book extends AbstractEntity {

	@Column(nullable = false)
	@Field(analyzer = @Analyzer(definition = "customanalyzer"))
	private String title;

	@Column(nullable = false)
	private Integer pages;

	@Column(nullable = false, unique = true)
	@Field
	private String isbn;

	@ManyToOne
	@IndexedEmbedded
	private Author author;

	@Lob
	private String description;

	@Temporal(TemporalType.DATE)
	@Field(index = Index.YES, analyze = Analyze.NO, store = Store.YES)
	@DateBridge(resolution = Resolution.YEAR)
	@Column(name = "publish_date")
	private Date publishDate;

	@Enumerated(EnumType.STRING)
	@Column(name = "edition_language")
	private Language language;

	@ManyToMany
	@JoinTable(name = "BOOK_GENRES", 
		joinColumns = @JoinColumn(name = "BOOK_ID"), 
		inverseJoinColumns = @JoinColumn(name = "GENRE_ID"))
	private Set<Genre> genres = new HashSet<>();

	@Column(name = "image_url")
	private String imageUrl;

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public void addGenre(Genre genre) {
		this.genres.add(genre);
	}
	
	public Set<Genre> getGenres() {
		return Collections.unmodifiableSet(genres);
	}

}
