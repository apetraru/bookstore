package com.bk.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * User: ph
 * Date: 1/16/13
 */

public class Message {
    public static void addMessage(String id, String messageBody, FacesMessage.Severity severity) {
        FacesContext.getCurrentInstance().addMessage(id, new FacesMessage(severity, null, messageBody));
    }

    private Message() {
    }
}
