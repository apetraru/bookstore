package com.bk.predicate;

import com.bk.model.Genre;
import com.bk.model.QBook;
import com.mysema.query.types.Predicate;

/**
 * @author Andrei Petraru
 * Date: 1/28/13
 */
public final class BookPredicate {
	
	private BookPredicate() {
	}

    public static Predicate searchTitleOrAuthor(String searchTerm) {
        QBook book = QBook.book;
        return book.title.containsIgnoreCase(searchTerm)
            .or(book.author.name.containsIgnoreCase(searchTerm));
    }
    
    public static Predicate searchByGenre(Genre genre) {
    	QBook book = QBook.book;
    	return book.genres.contains(genre);
    }

}
