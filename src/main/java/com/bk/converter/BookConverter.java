package com.bk.converter;

import com.bk.model.Book;
import com.bk.service.BookService;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: ph
 * Date: 3/2/13
 */

@Component
public class BookConverter implements Converter {

    @Autowired
    BookService bookService;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value.isEmpty()) {
            return null;
        }
        return bookService.findById(Long.parseLong(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        }
        Book book = (Book) value;
        return String.valueOf(book.getId());
    }
}
