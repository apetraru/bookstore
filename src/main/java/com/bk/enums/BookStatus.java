package com.bk.enums;

/**
 * @author Andrei Petraru
 * Mar 16, 2013
 */
public enum BookStatus {
	READING("Reading"),
	READ("Read"),
	WANT_TO_READ("Want to Read");

    private String status;

    private BookStatus(String status) {
        this.status= status;
    }

    @Override
    public String toString() {
        return status;
    }
}
