/*
 *  Desenvolvido pela equipe Super-Bits.com CNPJ 20.019.971/0001-90

 */
package com.super_bits.modulosSB.SBCore.UtilGeral.criptografia;

import java.io.UnsupportedEncodingException;
import java.security.SecureRandom;
import java.util.Arrays;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * Encapsulates several password-related utility functions Some functions came
 * originally from http://www.securitydocs.com/library/3439
 *
 * Divulgado em : https://gist.github.com/opensource21/524356
 *
 * @author desenvolvedorninja01
 * @since 19/09/2019
 * @version 1.0
 */
public class PasswordTools {

    private static final String CARACTERES_PERMITIDOS_SENHA = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@,;.-=+";
    private static final String CARACTERES_PERMITIDOUSUARIO = "0123456789abcdefghijklmnopqrstuvwxyz.";
    private static final Hex CODEC = new Hex();

    public static String generateRandomPassword() {
        return generateRandomPassword(10, CARACTERES_PERMITIDOS_SENHA);
    }

    /**
     * Generates a random password
     *
     * @param size Password size
     * @returns Random password
     */
    static String generateRandomPassword(int size) {
        return generateRandomPassword(size, CARACTERES_PERMITIDOS_SENHA);
    }

    /**
     * Generates a random password out of a alowed characters
     *
     * @param size Password size
     * @param allowed String comprising the allowed characters
     * @returns Random password
     */
    static String generateRandomPassword(int size, String allowed) {
        SecureRandom random = new SecureRandom();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            int rand = random.nextInt(allowed.length());
            sb.append(allowed.substring(rand, rand + 1));
        }
        return sb.toString();
    }

    static String generateRandomLogin() {
        return generateRandomLogin(10);
    }

    /**
     * Returns a random string made up only of numbers, letters and the period
     * character
     */
    static String generateRandomLogin(int size) {
        return generateRandomPassword(size, CARACTERES_PERMITIDOUSUARIO);
    }

    static byte[] generateSalt() {
        return generateSalt(4);
    }

    /**
     * Generates a random salt of a certain size
     *
     * @param size How many bytes should be in the salt
     */
    static byte[] generateSalt(int size) {
        SecureRandom random = new SecureRandom();
        byte[] list = new byte[size];
        for (int i = 0; i < list.length; i++) {
            list[i] = (byte) random.nextInt();
        }
        return list;
    }

    /**
     * Combine two byte arrays
     *
     * @param l first byte array
     * @param r second byte array
     * @return byte[] combined byte array
     */
    private static byte[] concatenate(byte[] left, byte[] right) {
        byte[] b = new byte[left.length + right.length];
        System.arraycopy(left, 0, b, 0, left.length);
        System.arraycopy(right, 0, b, left.length, right.length);
        return b;
    }

    /**
     * split a byte array in two
     *
     * @param src byte array to be split
     * @param n element at which to split the byte array
     * @return byte[][] two byte arrays that have been split
     */
    private static byte[][] split(byte[] src, int n) {
        byte[] l, r;
        if (src == null || src.length <= n) {
            l = src;
            r = new byte[0];
        } else {
            l = new byte[n];
            r = new byte[src.length - n];
            System.arraycopy(src, 0, l, 0, n);
            System.arraycopy(src, n, r, 0, r.length);
        }
        byte[][] lr = {l, r};
        return lr;
    }

    /**
     * SHA password and a random salt.
     *
     * @param password String to hash
     * @return String Base64-encoded byte array concatenating the 32-byte hash
     * and the salt
     *
     */
    static byte[] saltPassword(String password) {
        final byte[] salt = generateSalt(4);
        final byte[] pwdBytes = password.getBytes();
        final byte[] hash = DigestUtils.sha(concatenate(pwdBytes, salt));
        return concatenate(hash, salt);
    }

    /**
     * Returns a salted password base64 encoded.
     *
     * @see PasswordTools#saltPassword(String)
     */
    static String saltPasswordBase64(String password) {
        String encoded = new String(Base64.encodeBase64(saltPassword(password)));
        return encoded;
    }

    /**
     * Returns a salted password hex-encoded
     *
     * @see PasswordTools#saltPassword(String)
     */
    static String saltPasswordHex(String password) {
        String encoded = new String(CODEC.encode(saltPassword(password)));
        return encoded;
    }

    /**
     * Verifies a password against a hex-encoded digest
     *
     * @throws DecoderException
     * @see PasswordTools#checkDigest(String,byte[])
     */
    static boolean checkDigestHex(String password, String digestHex) throws DecoderException {
        byte[] digest = CODEC.decode(digestHex.getBytes());
        return checkDigest(password, digest);
    }

    /**
     * Verifies a password against a base64-encoded digest
     *
     * @throws UnsupportedEncodingException
     * @see PasswordTools#checkDigest(String,byte[])
     */
    static boolean checkDigestBase64(String password, String digestBase64) throws UnsupportedEncodingException {
        byte[] digest = Base64.decodeBase64(digestBase64.getBytes("utf-8"));
        return checkDigest(password, digest);
    }

    /**
     * Verifies a password against a digest
     *
     * @param password Value to verify
     * @param digest byte array concatenating the 32-byte hash and the salt
     * @return boolean True if successful
     *
     */
    static boolean checkDigest(String password, byte[] digest) {

        // First section will contain the original hash, the next the salt
        // SHA-1 hashes are 20-bytes in length
        // SHA-256 hashes are 32-bytes in length
        final int byteLength = 20;
        byte[][] hs = split(digest, byteLength);
        byte[] hash = hs[0];
        byte[] salt = hs[1];

        // Update digest object with byte array of clear text string and salt
        final byte[] concat = concatenate(password.getBytes(), salt);

        // Complete hash computation, this is now binary data
        final byte[] pwhash;
        pwhash = DigestUtils.sha(concat);

        final boolean valid = Arrays.equals(hash, pwhash);

        return valid;
    }
}
