package com.thoughtworks.writer;

import com.thoughtworks.interpreter.SymbolInterpreter;
import com.thoughtworks.repository.CreditRepository;
import com.thoughtworks.repository.SymbolRepository;
import com.thoughtworks.util.RepositoryUtil;

import java.math.BigDecimal;
import java.util.Arrays;

public class CreditWriter implements Writer {
    private static final String PREFIX = "how many credits is ";
    private static final String SUFFIX = " ?";
    private static final java.lang.String DELIMITER = " ";
    private SymbolInterpreter symbolInterpreter;
    private SymbolRepository symbolRepository;
    private CreditRepository creditRepository;

    public CreditWriter() {
        symbolInterpreter = new SymbolInterpreter();
    }

    // Messy, would like to refactor into smaller functions but heavily intertwined steps
    public void process(String input) {
        String argumentString = extractVariablesFromInput(input);

        String[] argumentArray = argumentString.split(DELIMITER);
        String key = argumentArray[argumentArray.length - 1];
        String[] variables = Arrays.copyOfRange(argumentArray, 0, argumentArray.length - 1);
        String symbols = RepositoryUtil.lookupSymbolsFromVariables(symbolRepository, variables);

        writeOutput(argumentString, key, symbols);
    }

    private String extractVariablesFromInput(String input) {
        return input.substring(PREFIX.length(), input.length() - SUFFIX.length());
    }

    private void writeOutput(String variables, String key, String symbols) {
        BigDecimal credits = new BigDecimal(creditRepository.get(key)).multiply(symbolInterpreter.interpret(symbols));
        credits = credits.setScale(0);
        System.out.println(variables + " is " + credits.toString() + " Credits");
    }

    public void setSymbolRepository(SymbolRepository symbolRepository) {
        this.symbolRepository = symbolRepository;
    }

    public void setCreditRepository(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }
}
