package com.bk.bean;

import com.bk.model.Author;
import com.bk.model.Book;
import com.bk.model.Image;
import com.bk.repository.AuthorRepository;
import com.bk.service.BookService;
import com.bk.util.Language;
import com.bk.util.Message;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import org.apache.commons.lang3.StringUtils;
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
@Scope("view")
public class BookBean {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorRepository authorRepository;

    private Book book;
    private Author author;
    private final Date today = new Date();

    @PostConstruct
    public void init() {
        reset();
    }

    private Image convertToFileCover(UploadedFile file) {
        if (file == null) {
            return null;
        }
        Image image = new Image();
        image.setContentType(file.getContentType());
        image.setName(file.getFileName());
        image.setSize(file.getSize());
        image.setFileContent(file.getContents());
        return image;
    }

    private Author saveAuthorIfNew() {
        Author existingAuthor = authorRepository.findByName(author.getName());
        if (existingAuthor == null) {
            author.addBook(book);
            Author saved = authorRepository.save(author);
            if (saved == null) {
                Message.addMessage("addBookFormId:addBookButtonId", "Book saving failed", FacesMessage.SEVERITY_ERROR);
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
            Message.addMessage("addBookFormId:addBookButtonId", "Book saved successfully", FacesMessage.SEVERITY_INFO);
            reset();
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
        Image image = convertToFileCover(file);
        if (image != null) {
            book.setImage(image);
            Message.addMessage("addBookFormId:coverUploadId", "Current file: " + file.getFileName(), FacesMessage.SEVERITY_INFO);
        }
        else {
            Message.addMessage("addBookFormId:coverUploadId", "Error uploading file", FacesMessage.SEVERITY_ERROR);
        }
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
