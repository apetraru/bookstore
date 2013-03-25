package com.bk.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * @author Andrei Petraru
 * Date: 16 Jan 2013
 */

public final class Message {
    public static void addMessage(String id, String messageBody, FacesMessage.Severity severity) {
        FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(severity, null, messageBody));
    }

    private Message() {
    }
}
