package com.bk.predicate;

import com.bk.model.QBook;
import com.mysema.query.types.Predicate;
import org.springframework.data.domain.PageRequest;

/**
 * User: ph
 * Date: 1/28/13
 */
public class BookPredicate {

    public static Predicate searchTitleOrAuthor(String searchTerm) {
        QBook book = QBook.book;
        return book.title.containsIgnoreCase(searchTerm)
            .or(book.author.containsIgnoreCase(searchTerm));
    }

}
