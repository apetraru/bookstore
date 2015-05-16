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

	@Column(name = "year")
	private String year;

	@Enumerated(EnumType.STRING)
	@Column(name = "edition_language")
	private Language language;

	@ManyToMany
	@JoinTable(name = "BOOK_GENRES", 
		joinColumns = @JoinColumn(name = "BOOK_ID"), 
		inverseJoinColumns = @JoinColumn(name = "GENRE_ID"))
	private Set<Genre> genres = new HashSet<>();

	@Column(name = "img_small")
	private String imageSmall;

	@Column(name = "img_medium")
	private String imageMedium;

	@Column(name = "img_large")
	private String imageLarge;

	@Column(name = "publisher")
	private String publisher;

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

	public Language getLanguage() {
		return language;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getImageSmall() {
		return imageSmall;
	}

	public void setImageSmall(String imageSmall) {
		this.imageSmall = imageSmall;
	}

	public String getImageMedium() {
		return imageMedium;
	}

	public void setImageMedium(String imageMedium) {
		this.imageMedium = imageMedium;
	}

	public String getImageLarge() {
		return imageLarge;
	}

	public void setImageLarge(String imageLarge) {
		this.imageLarge = imageLarge;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public void addGenre(Genre genre) {
		this.genres.add(genre);
	}
	
	public Set<Genre> getGenres() {
		return Collections.unmodifiableSet(genres);
	}

}
