package com.bk.util;

/**
 * User: ph
 * Date: 1/26/13
 */
public enum Language {
    CHINESE("Chinese"),
    ENGLISH("English"),
    FRENCH("French"),
    GERMAN("German"),
    JAPANESE("Japanese"),
    ITALIAN("Italian"),
    ROMANIAN("Romanian"),
    SPANISH("Spanish");

    private String language;

    private Language(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return language;
    }
}
