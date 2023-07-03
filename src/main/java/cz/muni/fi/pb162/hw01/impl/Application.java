package cz.muni.fi.pb162.hw01.impl;

import com.beust.jcommander.Parameter;
import cz.muni.fi.pb162.hw01.CipherType;
import cz.muni.fi.pb162.hw01.Operation;
import cz.muni.fi.pb162.hw01.cmd.CipherTypeConverter;
import cz.muni.fi.pb162.hw01.cmd.CommandLine;
import cz.muni.fi.pb162.hw01.cmd.OperationConverter;
import cz.muni.fi.pb162.hw01.impl.ciphers.CaesarCipher;
import cz.muni.fi.pb162.hw01.impl.ciphers.MorseCode;
import cz.muni.fi.pb162.hw01.impl.ciphers.VigenereCipher;

/**
 * Application class represents the command line interface of this application.
 * <p>
 * You are expected to implement  the  {@link Application#run()} method
 *
 * @author jcechace
 */

public class Application {

    @Parameter(names = {"--cipher", "-c"}, converter = CipherTypeConverter.class)
    private CipherType cipherType = CipherType.MORSE_CODE;

    @Parameter(names = {"--operation", "-o"}, required = true, converter = OperationConverter.class)
    private Operation operation;

    @Parameter(names = {"--text", "-t"}, required = true)
    private String text;

    @Parameter(names = {"--shift"})
    private int caesarShift;

    @Parameter(names = {"--key"})
    private String vigenereKey;

    @Parameter(names = "--help", help = true)
    private boolean showUsage = false;

    /**
     * Application entry point
     *
     * @param args command line arguments of the application
     */
    public static void main(String[] args) {
        Application app = new Application();

        CommandLine cli = new CommandLine(app);
        cli.parseArguments(args);

        if (app.showUsage) {
            cli.showUsage();
        } else {
            app.run();
        }
    }

    /**
     * Application runtime logic
     */
    private void run() {
        switch (cipherType) {

            case VIGENERE:
                if (operation == Operation.ENCRYPT) {
                    VigenereCipher vigenere = new VigenereCipher(vigenereKey);
                    System.out.println(vigenere.encrypt(text));
                } else {
                    VigenereCipher vigeneredec = new VigenereCipher(vigenereKey);
                    System.out.println(vigeneredec.decrypt(text));
                }
                break;
            case MORSE_CODE:
                if (operation == Operation.ENCRYPT) {
                    MorseCode morse = new MorseCode();
                    System.out.println(morse.encrypt(text));
                } else {
                    MorseCode morsedec = new MorseCode();
                    System.out.println(morsedec.decrypt(text));
                }
                break;
            default:
                if (operation == Operation.ENCRYPT) {
                    CaesarCipher caesar = new CaesarCipher(caesarShift);
                    System.out.println(caesar.encrypt(text));
                } else {
                    CaesarCipher caesardec = new CaesarCipher(caesarShift);
                    System.out.println(caesardec.decrypt(text));
                }
                break;
        }


    }
}
