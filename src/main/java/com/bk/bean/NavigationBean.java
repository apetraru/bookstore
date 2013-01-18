package com.bk.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * User: ph
 * Date: 1/17/13
 */

@Component
@Scope("request")
public class NavigationBean {
    private static final String REDIRECT = "?faces-redirect=true";
    private static final String HOME = "/pages/home.jsf?";
    private static final String LOGIN = "/pages/login.jsf";
    private static final String REGISTER = "/pages/register.jsf";

    public static String LOGIN() {
        return LOGIN + REDIRECT;
    }

    public static String REGISTER() {
        return REGISTER + REDIRECT;
    }

    public static String HOME() {
        return HOME + REDIRECT;
    }
}
