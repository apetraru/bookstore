package com.bk.bean;

import com.bk.model.Book;
import com.bk.model.Image;
import com.bk.service.BookService;
import com.bk.util.Language;
import com.bk.util.Message;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * User: ph
 * Date: 1/28/13
 */

@Component
@Scope("session")
public class BookBean {

    @Autowired
    private BookService bookService;

    private Book book;
    private final Date today = new Date();

    @PostConstruct
    public void init() {
        resetBook();
    }

    public void save() {
        Book saved = bookService.save(book);
        if (saved != null) {
            Message.addMessage("addBookFormId:addBookButtonId", "Book saved successfully", FacesMessage.SEVERITY_INFO);
            resetBook();
        }
        else {
            Message.addMessage("addBookFormId:addBookButtonId", "Book saving failed", FacesMessage.SEVERITY_ERROR);
        }

    }

    public Language[] getLanguages() {
        return Language.values();
    }

    public void upload(FileUploadEvent event) {
        UploadedFile file = event.getFile();
        if (book.getImage() == null) {
            Image image = convertToFileCover(file);
            book.setImage(image);
            Message.addMessage("addBookFormId:coverUploadId", file.getFileName() + " uploaded", FacesMessage.SEVERITY_INFO);
        }
        else {
            Message.addMessage("addBookFormId:coverUploadId", "Cover already exists", FacesMessage.SEVERITY_ERROR);
        }
    }

    private Image convertToFileCover(UploadedFile file) {
        if (file == null) {
            return null;
        }
        Image cover = new Image();
        cover.setContentType(file.getContentType());
        cover.setName(file.getFileName());
        cover.setSize(file.getSize());
        cover.setFileContent(file.getContents());
        return cover;
    }

    public void resetBook() {
        book = new Book();
        book.setLanguage(Language.ENGLISH);
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
