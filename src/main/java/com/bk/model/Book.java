package com.bk.model;

import com.bk.util.Language;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author ph
 */

@Entity
public class Book extends AbstractEntity {

    private static final int MAX_LENGTH = 32000;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private Integer pages;

    @Column(nullable = false, unique = true)
    private String isbn;

    private String author;

    @Column(length = MAX_LENGTH)
    private String description;

    @Temporal(TemporalType.DATE)
    private Date publishDate;

    @Enumerated(EnumType.STRING)
    private Language language;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cover_id")
    private BookCover cover;

    public Book() {
    }

    public Book(String title, Integer pages, String isbn) {
        this.title = title;
        this.pages = pages;
        this.isbn = isbn;
    }

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
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

    public BookCover getCover() {
        return cover;
    }

    public void setCover(BookCover cover) {
        this.cover = cover;
    }
}
