package com.thoughtworks.repository;

import java.util.HashMap;
import java.util.Map;

public class SymbolRepository implements Repository {
    private static final String DELIMITER = " is ";
    private Map<String, String> symbols = new HashMap<String, String>();

    public String get(String key) {
        return symbols.get(key);
    }

    public void put(String input) {
        String[] args = input.split(DELIMITER);
        symbols.put(args[0], args[1]);
    }
}
