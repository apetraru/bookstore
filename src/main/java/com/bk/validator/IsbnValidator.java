package com.bk.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * User: ph
 * Date: 1/31/13
 */

@FacesValidator("isbnValidator")
public class IsbnValidator implements Validator {

    private static final String INVALID_ISBN = "Invalid ISBN format";

    @Override
    public void validate(FacesContext context, UIComponent component,
                         Object value) throws ValidatorException {

        int check = 0;

        try {
            String isbn = value.toString();
            if (isbn.length() == 13) {
                for (int i = 0; i < 12; i += 2) {
                    check += Integer.valueOf(isbn.substring(i, i + 1));
                }
                for (int i = 1; i < 12; i += 2) {
                    check += Integer.valueOf(isbn.substring(i, i + 1)) * 3;
                }
                check += Integer.valueOf(isbn.substring(12));
            }
            else {
                check = -1;
            }
        } catch (NumberFormatException e) {
            throw new ValidatorException(errorMessage());
        }

        if (check % 10 != 0) {
            throw new ValidatorException(errorMessage());
        }
    }

    private FacesMessage errorMessage() {
        FacesMessage msg = new FacesMessage("Invalid ISBN format");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        return msg;
    }
}
