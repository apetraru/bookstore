package com.bk.bean;

import com.bk.model.Book;
import com.bk.model.Rating;
import com.bk.repository.RatingRepository;
import com.bk.service.BookService;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.RateEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * User: ph
 * Date: 3/4/13
 */
@Component
@Scope("view")
public class BookBean implements Serializable {

    @Autowired
    private BookService bookService;

    @Autowired
    private RatingRepository ratingRepository;

    @Autowired
    private LoginBean loginBean;

    private Book book;
    private Long id;
    private Integer userRating;
    private Integer bookRating;

    public void init() {
        if (id == null) {
            String message = "Bad request. Please use a link from within the system.";
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
            return;
        }

        book = bookService.findById(id);

        if (book == null) {
            String message = "Bad request. Unknown book.";
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
        }

    }

    public void addRating(RateEvent rateEvent) {
        Rating rating = new Rating();
        rating.setBook(book);
        rating.setCustomer(loginBean.getLoggedInUser());
        rating.setRating(userRating);
        ratingRepository.save(rating);
    }

    public List<Book> search(String input) {
        return bookService.search(input, 0, 10);
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

    public Integer getUserRating() {
        return userRating;
    }

    public void setUserRating(Integer userRating) {
        this.userRating = userRating;
    }

    public Integer getBookRating() {
        return bookRating;
    }
}
