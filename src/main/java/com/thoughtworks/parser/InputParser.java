package com.thoughtworks.parser;

import com.thoughtworks.reader.CreditReader;
import com.thoughtworks.reader.SymbolReader;
import com.thoughtworks.writer.CreditWriter;
import com.thoughtworks.writer.SymbolWriter;
import com.thoughtworks.writer.UnknownWriter;

public class InputParser {
    private SymbolReader symbolReader;
    private CreditReader creditReader;
    private SymbolWriter symbolWriter;
    private CreditWriter creditWriter;
    private UnknownWriter unknownWriter;

    public String parse(String input) {
        return input;
    }

    public void parseSingleLine(String input) {

    }

    public void setSymbolReader(SymbolReader symbolReader) {
        this.symbolReader = symbolReader;
    }

    public void setCreditReader(CreditReader creditReader) {
        this.creditReader = creditReader;
    }

    public void setSymbolWriter(SymbolWriter symbolWriter) {
        this.symbolWriter = symbolWriter;
    }

    public void setCreditWriter(CreditWriter creditWriter) {
        this.creditWriter = creditWriter;
    }

    public void setUnknownWriter(UnknownWriter unknownWriter) {
        this.unknownWriter = unknownWriter;
    }
}
