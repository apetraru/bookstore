package com.bk.validator;

import static com.bk.util.Message.msg;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bk.model.Book;
import com.bk.service.BookService;

/**
 * @author Andrei Petraru
 * Date: 1/31/13
 */

@Component
@FacesValidator("isbnValidator")
public class IsbnValidator implements Validator {
	
	@Autowired private BookService bookService;

	private static final int THIRTEEN = 13;
	private static final int TWELVE = 12;
	private static final int TEN = 10;
	private static final int THREE = 3;
	private static final int TWO = 2;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) {

		int check = 0;
        String isbn = value.toString();

		try {
			if (isbn.length() == THIRTEEN) {
				for (int i = 0; i < TWELVE; i += TWO) {
					check += Integer.valueOf(isbn.substring(i, i + 1));
				}
				for (int i = 1; i < TWELVE; i += TWO) {
					check += Integer.valueOf(isbn.substring(i, i + 1)) * THREE;
				}
				check += Integer.valueOf(isbn.substring(TWELVE));
			}
			else {
				check = -1;
			}
		}
		catch (NumberFormatException ex) {
			Logger.getLogger(IsbnValidator.class.getName()).log(Level.SEVERE, null, ex);
		}

		if (check % TEN != 0) {
			throw new ValidatorException(errorMessage(msg("invalidISBN")));
		}
	
		Book book = bookService.findByIsbn(isbn);
		if (book != null) {
			throw new ValidatorException(errorMessage(msg("duplicateISBN")));
		}
		
	}

	private FacesMessage errorMessage(String message) {
		FacesMessage msg = new FacesMessage(message);
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		return msg;
	}
}
