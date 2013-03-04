package com.bk.converter;

import com.bk.bean.IndexBean;
import com.bk.model.Book;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 * User: ph
 * Date: 3/2/13
 */

@FacesConverter("bookConverter")
public class BookConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        IndexBean bean = (IndexBean) context.getApplication().evaluateExpressionGet(context, "#{indexBean}", IndexBean.class);
        List<Book> books = bean.getBooks();
        for (Book book : books) {
            if (book.getId().equals(Long.parseLong(value))) {
                return book;
            }
        }
        return null;
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
