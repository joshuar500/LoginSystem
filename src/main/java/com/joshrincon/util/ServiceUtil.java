package com.joshrincon.util;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by on 7/21/2014.
 */
public class ServiceUtil {

    public static String md5(String src) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] md5 = md.digest(src.getBytes());
            BASE64Encoder encoder = new BASE64Encoder();
            return encoder.encode(md5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
