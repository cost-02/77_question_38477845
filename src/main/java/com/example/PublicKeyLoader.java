package com.example;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.math.BigInteger;
import java.util.Base64;

public class PublicKeyLoader {

    // Converti da Base64 URL-safe a Base64 standard
    private static byte[] base64UrlDecode(String input) {
        String base64 = input.replace('-', '+').replace('_', '/');
        while (base64.length() % 4 != 0) {
            base64 += "=";
        }
        return Base64.getDecoder().decode(base64);
    }

    public static PublicKey getPublicKey(String modulusBase64, String exponentBase64) throws Exception {
        byte[] modulusBytes = base64UrlDecode(modulusBase64);
        byte[] exponentBytes = base64UrlDecode(exponentBase64);

        BigInteger modulus = new BigInteger(1, modulusBytes);
        BigInteger exponent = new BigInteger(1, exponentBytes);

        RSAPublicKeySpec spec = new RSAPublicKeySpec(modulus, exponent);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePublic(spec);
    }

    public static void main(String[] args) {
        try {
            String modulus = "vswzzDmrqLSHUu61YDxUhM87hjcVjg42NwpFOyLQK8CyW5YRcr1YUkFRNDbb92MTNW3CsSWJX3DSuilnxf8n3_JW-A9R5JAqwmEygYIXuFcoJ_pb923bph0-ayWPBfD-qwYrELvpiEHBf1QSLJYkRb1wzAlwhCeYJorifu2WhCZoOVVYQAEyNqYF7AVhNImioT8-lhFWGqHp2Jt7-oXtCjVVyyShRHUMYyCRzGj1VGI6AU5DgVebXYD2GJawUhX-AD2CzsX8lMXeaVu88sBU9XLL1Zb_cOvAC7wTXxcls0taKx-8PiWUWKjSg0-O2ZXbfFROyQpQYHQH0BkO8XRh8w";
            String exponent = "AQAB";

            PublicKey publicKey = getPublicKey(modulus, exponent);
            System.out.println("Public Key: " + publicKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
