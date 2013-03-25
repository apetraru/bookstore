package com.bk.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Andrei Petraru
 * Date: 1/17/13
 */
public final class PasswordHash {

    private static final String ALGORITHM = "SHA-256";

    public static String hash(String password)
        throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md = MessageDigest.getInstance(ALGORITHM);
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    private PasswordHash() {
    }
}
