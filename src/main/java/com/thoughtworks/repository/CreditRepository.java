package com.thoughtworks.repository;

import com.thoughtworks.interpreter.SymbolInterpreter;

import java.math.BigDecimal;
import java.util.*;

public class CreditRepository implements Repository {
    private static final String DELIMITER = " ";
    private static final String ANCHOR_TEXT = "is";
    private Map<String, BigDecimal> credits = new HashMap<String, BigDecimal>();
    private SymbolRepository symbolRepository;
    private SymbolInterpreter symbolInterpreter;

    public String get(String key) {
        return credits.get(key).toString();
    }

    public void put(String input) {
        String[] args = input.split(DELIMITER);
        int anchorPos = findArrayPositionForWordIs(args);
        processArgumentsAndPutToMap(args, anchorPos);
    }

    private int findArrayPositionForWordIs(String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (ANCHOR_TEXT.equals(array[i])) {
                return i;
            }
        }

        return -1;
    }

    private void processArgumentsAndPutToMap(String[] args, int anchorPos) {
        String key = args[anchorPos - 1];
        BigDecimal value = new BigDecimal(args[anchorPos + 1]);
        BigDecimal multipler = getDecimalFromVariables(Arrays.copyOfRange(args, 0, anchorPos - 1));

        credits.put(key, value.divide(multipler));
    }

    private BigDecimal getDecimalFromVariables(String[] variables) {
        String symbols = lookupSymbolsFromVariables(variables);
        return getNumberFromSymbols(symbols);
    }

    private String lookupSymbolsFromVariables(String[] keys) {
        StringBuffer symbols = new StringBuffer();

        for (String key : keys) {
            symbols.append(symbolRepository.get(key));
        }

        return symbols.toString();
    }

    private BigDecimal getNumberFromSymbols(String symbols) {
        return symbolInterpreter.interpret(symbols);
    }

    public void setSymbolRepository(SymbolRepository symbolRepository) {
        this.symbolRepository = symbolRepository;
    }

    public void setSymbolInterpreter(SymbolInterpreter symbolInterpreter) {
        this.symbolInterpreter = symbolInterpreter;
    }
}
