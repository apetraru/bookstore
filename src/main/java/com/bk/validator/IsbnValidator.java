package com.bk.validator;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @author Andrei Petraru
 * Date: 1/31/13
 */

@FacesValidator("isbnValidator")
public class IsbnValidator implements Validator {

	private static final String INVALID_ISBN = "Invalid ISBN format";
	private static final int THIRTEEN = 13;
	private static final int TWELVE = 12;
	private static final int TEN = 10;
	private static final int THREE = 3;
	private static final int TWO = 2;

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) {

		int check = 0;

		try {
			String isbn = value.toString();
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
			throw new ValidatorException(errorMessage());
		}
	}

	private FacesMessage errorMessage() {
		FacesMessage msg = new FacesMessage(INVALID_ISBN);
		msg.setSeverity(FacesMessage.SEVERITY_ERROR);
		return msg;
	}
}
