package com.bk.util;

import java.util.ResourceBundle;

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
    
    public static void error(String id, String messageBody) {
    	addMessage(id, messageBody, FacesMessage.SEVERITY_ERROR);
    }
    
    public static void info(String id, String messageBody) {
    	addMessage(id, messageBody, FacesMessage.SEVERITY_INFO);
    }
    
    public static String msg(String key) {
    	return ResourceBundle.getBundle("messages").getString(key);
    }
}
