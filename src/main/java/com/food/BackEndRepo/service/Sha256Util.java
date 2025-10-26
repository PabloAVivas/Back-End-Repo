package com.food.BackEndRepo.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Sha256Util {
    //Se recibe un texto String y este lo devuelve hasheado
    public static String hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] dig = md.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder bytesToHex = new StringBuilder();

            for (byte b : dig){
                bytesToHex.append(String.format("%02x", b));
            }

            return bytesToHex.toString();

        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }

}
