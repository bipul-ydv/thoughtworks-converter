package com.thoughtworks.interpreter;

import javafx.util.Pair;

import java.math.BigDecimal;

public class SymbolInterpreter {
    private static final java.lang.String DELIMITER = " ";
    private static final String REGEX_SYMBOLS = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
    public final static Pair[] SUBSTITUTIONS = {
            new Pair<String, String>("IV", " 4 "),
            new Pair<String, String>("IX", " 9 "),
            new Pair<String, String>("XL", " 40 "),
            new Pair<String, String>("XC", " 90 "),
            new Pair<String, String>("CD", " 400 "),
            new Pair<String, String>("CM", " 900 "),
            new Pair<String, String>("I", " 1 "),
            new Pair<String, String>("V", " 5 "),
            new Pair<String, String>("X", " 10 "),
            new Pair<String, String>("L", " 50 "),
            new Pair<String, String>("C", " 100 "),
            new Pair<String, String>("D", " 500 "),
            new Pair<String, String>("M", " 1000 ")
    };

    public BigDecimal interpret(String input) {
        validateInput(input);
        String decimalString = convertToDecimalStringWithSpaces(input);
        return sumDecimalString(decimalString);
    }

    private void validateInput(String input) {
        if (!input.matches(REGEX_SYMBOLS)) {
            throw new IllegalArgumentException("Invalid symbol in the input: " + input);
        }
    }

    private String convertToDecimalStringWithSpaces(String input) {
        String inputCopy = input;

        for (Pair<String, String> sub : SUBSTITUTIONS) {
            inputCopy = inputCopy.replaceAll(sub.getKey(), sub.getValue());
        }

        return inputCopy;
    }

    private BigDecimal sumDecimalString(String decimalString) {
        BigDecimal sum = new BigDecimal(0);

        for (String element : decimalString.trim().replaceAll("  ", " ").split(DELIMITER)) {
            sum = sum.add(new BigDecimal(element.trim()));
        }

        return sum;
    }
}
