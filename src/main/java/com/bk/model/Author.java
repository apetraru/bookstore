package com.bk.model;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;

import com.bk.util.Gender;

/**
 * @author Andrei Petaru
 * Date: 2/2/13
 */

@Entity
public class Author extends AbstractEntity {

	@Field(analyzer = @Analyzer(definition = "customanalyzer"))
	private String name;
	private Gender gender;
	private String website;
	private String imageUrl;

	@Temporal(TemporalType.DATE)
	private Date birthDate;

	@Lob
	private String about;

	@ContainedIn
	@OneToMany(mappedBy = "author")
	private Set<Book> books = new HashSet<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public Set<Book> getBooks() {
		return Collections.unmodifiableSet(books);
	}

	public void addBook(Book book) {
		this.books.add(book);
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
