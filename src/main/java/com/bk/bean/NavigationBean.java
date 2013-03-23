package com.bk.bean;

import org.springframework.stereotype.Component;

/**
 * @author Andrei Petraru 
 * Date: 1/17/13
 */
@Component
public class NavigationBean {

	private static final String REDIRECT = "?faces-redirect=true";
	private static final String HOME = "/pages/home.jsf";
	private static final String LOGIN = "/pages/login.jsf";
	private static final String REGISTER = "/pages/register.jsf";
	private static final String ADD_BOOK = "/pages/admin/addBook.jsf";
	private static final String BOOK = "/pages/book.jsf";
	private static final String AUTHOR = "/pages/author.jsf";
	private static final String GENRE = "/pages/genre.jsf";
	private static final String SEARCH = "/pages/search.jsf";

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

	public static String book() {
		return BOOK;
	}
	
	public static String author() {
		return AUTHOR;
	}
	
	public static String genre() {
		return GENRE;
	}
	
	public static String search() {
		return SEARCH + REDIRECT;
	}
}
