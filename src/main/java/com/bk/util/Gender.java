package com.bk.util;

/**
 * User: ph
 * Date: 2/2/13
 */
public enum Gender {
    MALE("Male"),
    FEMALE("Female");

    private String gender;

    private Gender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return gender;
    }
}
