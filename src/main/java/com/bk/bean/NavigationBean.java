package com.bk.bean;

import org.springframework.stereotype.Component;

/**
 * @author Andrei Petraru
 * 17 Jan 2013
 */
@Component
public final class NavigationBean {

	private static final String REDIRECT = "?faces-redirect=true";
	private static final String HOME = "/pages/home.jsf";
	private static final String LOGIN = "/pages/login.jsf";
	private static final String REGISTER = "/pages/register.jsf";
	private static final String ADD_BOOK = "/pages/admin/addBook.jsf";
	private static final String ADD_AUTHOR = "/pages/admin/addAuthor.jsf";
	private static final String BOOK = "/pages/book.jsf";
	private static final String AUTHOR = "/pages/author.jsf";
	private static final String GENRE = "/pages/genre.jsf";
	private static final String SEARCH = "/pages/search.jsf";
	private static final String NO_ACCESS = "/pages/noaccess.jsf";
	private static final String CUSTOMER = "/pages/customer/details.jsf";
	private static final String USER = "/pages/user.jsf";
	private static final String RECOMMENDATIONS = "/pages/recommendations.jsf";

	private NavigationBean() {
	}

	public static String login() {
		return LOGIN + REDIRECT;
	}

	public static String register() {
		return REGISTER + REDIRECT;
	}

	public static String home() {
		return HOME + REDIRECT;
	}

	public static String addBook() {
		return ADD_BOOK + REDIRECT;
	}

	public static String addAuthor() {
		return ADD_AUTHOR + REDIRECT;
	}

	public static String book() {
		return BOOK + REDIRECT;
	}

	public static String author() {
		return AUTHOR + REDIRECT;
	}

	public static String genre() {
		return GENRE + REDIRECT;
	}

	public static String search() {
		return SEARCH + REDIRECT;
	}

	public static String noAccess() {
		return NO_ACCESS + REDIRECT;
	}

	public static String profile() {
		return CUSTOMER + REDIRECT;
	}

	public static String user() {
		return USER + REDIRECT;
	}

	public static String recommendations() {
		return RECOMMENDATIONS + REDIRECT;
	}

}
