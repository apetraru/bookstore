package com.bk.enums;

/**
 * @author Andrei Petraru
 * Mar 16, 2013
 */
public enum Status {
	WANT_TO_READ("Want to Read"),
	READING("Currently Reading"),
	READ("Read");

    private String status;

    private Status(String status) {
        this.status= status;
    }

    @Override
    public String toString() {
        return status;
    }
}
