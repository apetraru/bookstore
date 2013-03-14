package com.bk.bean;

import com.bk.model.Customer;
import com.bk.service.CustomerService;
import com.bk.util.Message;
import com.bk.util.PasswordHash;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * User: ph
 * Date: 1/15/13
 */

@Component
@Scope("session")
public class LoginBean implements Serializable{

    private boolean loggedOn = false;
    private String username;
    private String password;
    private Customer loggedInUser;

    @Autowired
    private CustomerService customerService;

    public String login() {
        if (verifyCredentials()) {
            loggedOn = true;
            Message.addMessage("loginFormId:loginButtonId", "Welcome back " + username + "! Redirecting...",
                FacesMessage.SEVERITY_INFO);
            clearFields();
            return NavigationBean.home();
        }
        clearFields();
        return null;
    }

    public String logout() {
        loggedInUser = null;
        loggedOn = false;
        return NavigationBean.home();
    }

    private boolean verifyCredentials() {
        loggedInUser = customerService.findByUsername(username);
        if (loggedInUser == null) {
            addErrorMessage();
            return false;
        }

        if (!StringUtils.equals(username, loggedInUser.getUsername())) {
            addErrorMessage();
            return false;
        }

        String pass = loggedInUser.getPassword();
        String loginPass = hashPassword();
        if (!StringUtils.equals(pass, loginPass)) {
            addErrorMessage();
            return false;
        }

        return StringUtils.equals(username, loggedInUser.getUsername()) && (StringUtils.equals(pass, loggedInUser.getPassword()));

    }

    private void addErrorMessage() {
        Message.addMessage("loginFormId:loginButtonId", "Incorrect username or password", FacesMessage.SEVERITY_ERROR);
    }

    private String hashPassword() {
        try {
            return PasswordHash.hash(password);
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            Message.addMessage("loginFormId:loginButtonId", "Login failed!", FacesMessage.SEVERITY_ERROR);
            return null;
        }
    }

    private void clearFields() {
        username = "";
        password = "";
    }

    public boolean isLoggedOn() {
        return loggedOn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Customer getLoggedInUser() {
        return loggedInUser;
    }
}
