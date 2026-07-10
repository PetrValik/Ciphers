# Ciphers

A command-line tool in Java that encrypts and decrypts text using three classic substitution ciphers: **Morse Code**, **Caesar**, and **Vigenère**.

## About

This is a university assignment for **Masaryk University, course PB162 (Java)**. The task focuses as much on clean object-oriented design as on functional correctness: each cipher implements a shared `Cipher` interface (`encrypt` / `decrypt` over a fixed 62-character alphabet), and the command-line front end is decoupled from the cipher implementations.

The program reads text from the command line, applies the selected cipher and operation (encrypt or decrypt), and prints the result. Characters outside the supported alphabet (`A–Z`, `a–z`, `0–9`) are left untouched, except where a cipher defines otherwise. No AI tools were used during development.

## Tech stack

- **Java 11**
- **Maven** — build, packaging, and dependency management
- **JCommander** — command-line argument parsing
- **JUnit 5** + **AssertJ** — testing
- **Checkstyle** — enforced code style (build fails on violations)
- Packaged into a single runnable JAR via the Maven Shade plugin

## Build & run

Build and package (runs the tests and Checkstyle):

```bash
mvn clean install
```

To build while skipping style enforcement:

```bash
mvn clean install -Dcheckstyle.fail=false
```

The build produces a self-contained executable JAR at `target/application.jar`:

```bash
# Morse code (default cipher) — encrypt
java -jar target/application.jar -o ENCRYPT -t "Ahoj, jak se mas?"
# .-|....|---|.---|--..--||.---|.-|-.-||...|.||--|.-|...|..--..|

# Caesar cipher — encrypt with a shift of 3
java -jar target/application.jar -c CAESAR -o ENCRYPT --shift 3 -t "Ahoj, jak se mas?"
# Dkrm, mdn vh pdv?

# Caesar cipher — decrypt
java -jar target/application.jar -c CAESAR -o DECRYPT --shift 3 -t "Dkrm, mdn vh pdv?"
# Ahoj, jak se mas?

# Vigenère cipher — encrypt with a keyword
java -jar target/application.jar -c VIGENERE -o ENCRYPT --key BananaPapple -t "Ahoj, jak se mas?"
# B7R9, y0P T8 CDI?
```

## The ciphers

- **Morse Code** — encodes each character into a sequence of dots and dashes; letters are separated by `|` and spaces by `||`. Case-insensitive.
- **Caesar** — shifts each letter a fixed number of positions along the alphabet, wrapping around with modulo.
- **Vigenère** — a series of interwoven Caesar ciphers driven by a repeating keyword.

## Notes

University assignment — Masaryk University, Faculty of Informatics, **PB162 (Programming in Java)**, homework 1 (2021). The repository keeps the original assignment specification alongside my implementation under `cz.muni.fi.pb162.hw01.impl`.
