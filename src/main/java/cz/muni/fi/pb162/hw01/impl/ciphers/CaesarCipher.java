package cz.muni.fi.pb162.hw01.impl.ciphers;

import cz.muni.fi.pb162.hw01.Utils;

/**
 * @author Petr Valik
 */
public class CaesarCipher implements Cipher {
    public static final String ALPHABET =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                    "abcdefghijklmnopqrstuvwxyz" +
                    "0123456789";
    private final int shift;

    /**
     * initialize values of CeaserCipher
     *
     * @param shift move in ALPHABET
     * @author Petr Valik
     */

    public CaesarCipher(int shift) {
        this.shift = shift;
    }

    /**
     * Core function of encrypt
     *
     * @param input input String
     * @return encrypted String
     * @author Petr Valik
     */

    public String encrypt(String input) {
        String shiftAlphabet = alphabetShiftENC();
        return deEncrypting(shiftAlphabet, input);
    }

    /**
     * Core function of decrypt
     *
     * @param input input String
     * @return decrypted String
     * @author Petr Valik
     */

    public String decrypt(String input) {
        String shiftAlphabet = alphabetShiftDEC();
        return deEncrypting(shiftAlphabet, input);
    }

    /**
     * encode or decode String depend on shiftAlphabet
     *
     * @param shiftAlphabet shifted ALPHABET
     * @param input         input String
     * @return changed String
     * @author Petr Valik
     */

    private String deEncrypting(String shiftAlphabet, String input) {
        int length = input.length();

        StringBuilder output = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char converted = toSingleChar(i, shiftAlphabet, input);
            output.insert(i, converted);

        }
        return output.toString();
    }

    /**
     * encode or decode single char
     *
     * @param numIndex      index in plainText
     * @param shiftAlphabet Shifted ALPHABET
     * @param input         input String
     * @return changed char
     * @author Petr Valik
     */

    private char toSingleChar(int numIndex, String shiftAlphabet, String input) {
        char actual = input.charAt(numIndex);
        int index = Utils.searchIndex(ALPHABET.toCharArray(), actual);
        if (index == -1) {
            return actual;
        }
        return shiftAlphabet.charAt(index);
    }

    /**
     * shift alphabet for encode
     *
     * @return String shiftedAlphabet
     * @author Petr Valik
     */
    private String alphabetShiftENC() {
        String shift1 = ALPHABET.substring(0, shift % 62);
        String shift2 = ALPHABET.substring(shift % 62);
        return shift2 + shift1;
    }

    /**
     * shift alphabet for decode
     *
     * @return String shiftedAlphabet
     * @author Petr Valik
     */

    private String alphabetShiftDEC() {
        String shift1 = ALPHABET.substring(62 - (shift % 62));
        String shift2 = ALPHABET.substring(0, 62 - (shift % 62));
        return shift1 + shift2;
    }

}

