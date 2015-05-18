package com.thoughtworks.writer;

import com.thoughtworks.interpreter.SymbolInterpreter;
import com.thoughtworks.repository.CreditRepository;
import com.thoughtworks.repository.Repository;
import com.thoughtworks.repository.SymbolRepository;
import com.thoughtworks.util.RepositoryUtil;
import sun.jvm.hotspot.debugger.cdbg.Sym;

public class SymbolWriter implements Writer {
    private static final String PREFIX = "how much is ";
    private static final String SUFFIX = " ?";
    private static final java.lang.String DELIMITER = " ";
    private SymbolInterpreter symbolInterpreter;
    private SymbolRepository symbolRepository;

    public SymbolWriter() {
        symbolInterpreter = new SymbolInterpreter();
    }

    public void process(String input) {
        String variables = extractVariablesFromInput(input);
        String symbols = RepositoryUtil.lookupSymbolsFromVariables(symbolRepository, variables.split(DELIMITER));
        writeOutput(variables, symbols);
    }

    private String extractVariablesFromInput(String input) {
        return input.substring(PREFIX.length(), input.length() - SUFFIX.length());
    }

    private void writeOutput(String variables, String symbols) {
        System.out.println(variables + " is " + symbolInterpreter.interpret(symbols));
    }

    public void setSymbolRepository(SymbolRepository symbolRepository) {
        this.symbolRepository = symbolRepository;
    }

    public void setCreditRepository(CreditRepository creditRepository) {
    }
}
