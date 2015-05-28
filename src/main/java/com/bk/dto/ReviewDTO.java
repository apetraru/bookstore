package com.bk.dto;

import java.io.Serializable;

/**
 *
 * @author apetraru
 */
public class ReviewDTO implements Serializable{
	private Long bookId;
	private Long customerId;

	public ReviewDTO(Long bookId, Long customerId) {
		this.bookId = bookId;
		this.customerId = customerId;
	}

	
	
}
