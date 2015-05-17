package com.thoughtworks.parser;

import com.thoughtworks.repository.CreditRepository;
import com.thoughtworks.repository.Repository;
import com.thoughtworks.repository.SymbolRepository;
import com.thoughtworks.writer.CreditWriter;
import com.thoughtworks.writer.SymbolWriter;
import com.thoughtworks.writer.UnknownWriter;
import com.thoughtworks.writer.Writer;

public class InputParser {
    private static final String WRITER_LINE_PREFIX = "how ";
    private static final String REGEX_SET_SYMBOL = "^[a-z]+( is )(i|v|x|l|c|d|m)$";
    private static final String REGEX_SET_CREDIT = "^[a-z ]*[a-z]+( is )[0-9]+( credits)$";
    private static final String REGEX_OUTPUT_SYMBOL = "^(how much is )[a-z ]+(\\?)$";
    private static final String REGEX_OUTPUT_CREDIT = "^(how many credits is )[a-z ]+(\\?)$";
    private SymbolRepository symbolRepository;
    private CreditRepository creditRepository;
    private SymbolWriter symbolWriter;
    private CreditWriter creditWriter;
    private UnknownWriter unknownWriter;

    public void parse(String input) {
        for (String line : input.split("\\r?\\n")) {
            normaliseAndParseSingleLine(line);
        }
    }

    public void normaliseAndParseSingleLine(String input) {
        String normalisedInput = input.toLowerCase().trim();

        if (normalisedInput.startsWith(WRITER_LINE_PREFIX)) {
            parseWriterLine(normalisedInput);
        }
        else {
            parseRepositoryLine(normalisedInput);
        }
    }

    private void parseWriterLine(String input) {
        Writer writer;

        if (input.matches(REGEX_OUTPUT_SYMBOL)) {
            writer = symbolWriter;
        }
        else if (input.matches(REGEX_OUTPUT_CREDIT)) {
            writer = creditWriter;
        }
        else {
            writer = unknownWriter;
        }

        writer.process(input);
    }

    private void parseRepositoryLine(String input) {
        Repository repository;

        if (input.matches(REGEX_SET_SYMBOL)) {
            repository = symbolRepository;
        }
        else if (input.matches(REGEX_SET_CREDIT)) {
            repository = creditRepository;
        }
        else {
            unknownWriter.process(input);
            return;
        }

        repository.process(input);
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

    public void setSymbolRepository(SymbolRepository symbolRepository) {
        this.symbolRepository = symbolRepository;
    }

    public void setCreditRepository(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }
}
