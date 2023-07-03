package cz.muni.fi.pb162.hw01.impl.ciphers;

import cz.muni.fi.pb162.hw01.Utils;

/**
 * @author Petr Valik
 */
public class VigenereCipher implements Cipher {
    public static final String ALPHABET =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                    "abcdefghijklmnopqrstuvwxyz" +
                    "0123456789";
    private final String key;

    /**
     * initialize values of VigenereCipher
     *
     * @param key coding String
     * @author Petr Valik
     */
    public VigenereCipher(String key) {
        this.key = key;
    }

    /**
     * Core function of encrypt
     * encrypt text given in input
     *
     * @param input input String
     * @return encrypted String
     * @author Petr Valik
     */
    public String encrypt(String input) {
        int length = input.length();
        StringBuilder output = new StringBuilder(length);
        int[] numKey = keyCreator();

        for (int i = 0; i < length; i++) {
            int keyLength = numKey.length;
            int actualKey = numKey[(i % keyLength)];
            char charActual = input.charAt(i);
            CaesarCipher caesarshift = new CaesarCipher(actualKey);
            String encrypted = caesarshift.encrypt(String.valueOf(charActual));
            output.insert(i, encrypted.toCharArray()[0]);
        }
        return output.toString();
    }

    /**
     * Core function of decrypt
     * decode text given in input
     *
     * @param input input String
     * @return decrypted String
     * @author Petr Valik
     */
    public String decrypt(String input) {
        int length = input.length();
        StringBuilder output = new StringBuilder(length);
        int[] numKey = keyCreator();

        for (int i = 0; i < length; i++) {
            int keyLength = numKey.length;
            int actualKey = numKey[(i % keyLength)];
            char charActual = input.charAt(i);
            CaesarCipher caesarshift = new CaesarCipher(actualKey);
            String decrypted = caesarshift.decrypt(String.valueOf(charActual));
            output.insert(i, decrypted.toCharArray()[0]);
        }
        return output.toString();
    }

    /**
     * Creat array of shifts for CaesarCipher
     *
     * @return array of int
     * @author Petr Valik
     */
    public int[] keyCreator() {
        int length = key.length();
        int[] createdKey = new int[length];
        for (int i = 0; i < length; i++) {
            createdKey[i] = Utils.searchIndex(ALPHABET.toCharArray(), key.charAt(i));
        }
        return createdKey;
    }
}
