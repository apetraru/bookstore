package com.bk.util;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * @author Andrei Petraru
 * 29 Apr 2013
 */
public class PasswordHashTest {

	private static final String EMPTY_PASSWORD = "";
	private static final String PASSWORD = "12345";
	private static final String HASHED_PASSWORD = "00ac5eaee5c4cfdea2a2f8d65d3f73250a158043b0e35bdea1c406ae93347d376927ccedadf61d45006e48aa4dc719ba8043897152a0c5fcdefb36d23255aa95";

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
