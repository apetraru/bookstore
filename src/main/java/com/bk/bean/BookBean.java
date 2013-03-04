package com.bk.bean;

import com.bk.model.Book;
import com.bk.service.BookService;
import java.io.ByteArrayInputStream;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * User: ph
 * Date: 3/4/13
 */
@Component
@Scope("session")
public class BookBean implements Serializable {

    @Autowired
    private BookService bookService;

    private Book book;
    private Long id;
    private StreamedContent image;

    public void init() {
        if (id == null) {
            String message = "Bad request. Please use a link from within the system.";
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            return;
        }

        book = bookService.findById(id);
        if (book != null) {
            image = new DefaultStreamedContent(new ByteArrayInputStream(book.getImage().getFileContent()));
        }

        if (book == null) {
            String message = "Bad request. Unknown book.";
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }
    }

    public StreamedContent getImage() {
        return this.image;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
