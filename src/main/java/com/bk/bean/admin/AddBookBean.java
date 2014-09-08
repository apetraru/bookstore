package com.bk.bean.admin;

import static com.bk.util.Message.info;
import static com.bk.util.Message.msg;
import static com.bk.util.Message.error;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bk.enums.Language;
import com.bk.model.Author;
import com.bk.model.Book;
import com.bk.repository.AuthorRepository;
import com.bk.service.BookService;

/**
 * @author Andrei Petraru
 * Date: 1/28/13
 */

@Component
@Scope("request")
public class AddBookBean {
	
	private static final String ADD_BUTTON = "addBookForm:addBook";

    @Autowired private BookService bookService;
    @Autowired private AuthorRepository authorRepository;

    private Book book;
    private Author author;
    private final Date today = new Date();

    @PostConstruct
    public void init() {
        reset();
    }

    private Author saveAuthorIfNew() {
        Author existingAuthor = authorRepository.findByName(author.getName());
        if (existingAuthor == null) {
            author.addBook(book);
            Author saved = authorRepository.save(author);
            if (saved == null) {
                error(ADD_BUTTON, msg("bookSavingFailed"));
            }
            return saved;
        }
        return existingAuthor;
    }

    private void addAuthorToBook() {
        if (StringUtils.isNotBlank(author.getName())) {
            Author authorToSave = saveAuthorIfNew();
            book.setAuthor(authorToSave);
        }
    }

    public void save() {
        addAuthorToBook();
        Book saved = bookService.save(book);
        if (saved != null) {
            info(ADD_BUTTON, msg("bookSavingSuccess"));
            reset();
        }
        else {
        	error(ADD_BUTTON, msg("bookSavingFailed"));
        }
    }

    public Language[] getLanguages() {
        return Language.values();
    }

    public void reset() {
        author = new Author();
        book = new Book();
        book.setLanguage(Language.ENGLISH);
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getToday() {
        return today;
    }

}
