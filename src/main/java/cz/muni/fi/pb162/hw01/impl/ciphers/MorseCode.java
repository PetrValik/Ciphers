package cz.muni.fi.pb162.hw01.impl.ciphers;

import cz.muni.fi.pb162.hw01.Utils;

/**
 * @author Petr Valik
 */
public class MorseCode implements Cipher {
    public static final String ALPHABET =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                    "0123456789.,?!";
    public static final String[] MORSECODE = {".-", "-...", "-.-.", "-..", ".",
            "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--",
            "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-",
            ".--", "-..-", "-.--", "--..", "-----", ".----", "..---",
            "...--", "....-", ".....", "-....", "--...", "---..", "----.",
            ".-.-.-", "--..--", "..--..", "..--."};

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
        input = input.toUpperCase();
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < length; i++) {
            output.append(toMorse(i, input));
        }
        return output.toString();
    }

    /**
     * Core function of decrypt
     * encrypt text given in input
     *
     * @param input input String
     * @return encrypted String
     * @author Petr Valik
     */
    public String decrypt(String input) {
        String[] inputArray = input.split("\\|");
        int length = inputArray.length;
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < length; i++) {
            output.append(fromMorse(i, inputArray));
        }
        return output.toString().toLowerCase();
    }

    /**
     * encrypt char to MORSE
     *
     * @param i     index of char in input
     * @param input input String
     * @return encrypted morse
     * @author Petr Valik
     */
    public String toMorse(int i, String input) {
        int index = Utils.searchIndex(ALPHABET.toCharArray(), input.charAt(i));
        if (index == -1) {
            return "|";
        }
        return MORSECODE[index] + "|";
    }

    /**
     * encrypt MORSE to char
     *
     * @param i          index of char in input
     * @param inputArray input divided in array
     * @return encrypted char
     * @author Petr Valik
     */
    public String fromMorse(int i, String[] inputArray) {
        int index = Utils.searchIndex(MORSECODE, inputArray[i]);
        if (index == -1) {
            return " ";
        }
        return String.valueOf(ALPHABET.toCharArray()[index]);
    }
}