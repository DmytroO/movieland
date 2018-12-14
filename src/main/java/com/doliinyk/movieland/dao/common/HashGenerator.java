package com.doliinyk.movieland.dao.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {
    private static final Logger log = LoggerFactory.getLogger(HashGenerator.class);

    public static String getHash(String msg, String salt) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update((salt + msg).getBytes());
            byte[] digest = md.digest();
            return DatatypeConverter.printHexBinary(digest).toUpperCase();
        } catch (Exception e) {
            log.error("MessageDigest.getInstance failed\n {}", e);
            return msg;
        }
    }
}
