package com.bk.util;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Andrei Petraru
 * 29 Apr 2013
 */
public class PasswordHashTest {

	private static final String EMPTY_PASSWORD = "";
	private static final String PASSWORD = "1234";
	private static final String HASHED_PASSWORD = "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4";
	
	@Test
	public void validPasswordHashTest() {
		String hash = PasswordHash.hash(PASSWORD); 
		assertEquals(hash, HASHED_PASSWORD);
	}
	
	@Test
	public void emptyPasswordHashTest() {
		String hash = PasswordHash.hash(EMPTY_PASSWORD);
		assertEquals(hash, null);
	}
}
