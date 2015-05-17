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

        if (input.startsWith("how much")) {
            writer = symbolWriter;
        }
        else if (input.startsWith("how many")) {
            writer = creditWriter;
        }
        else {
            writer = unknownWriter;
        }

        writer.process(input);
    }

    private void parseRepositoryLine(String input) {
        Repository repository;

        if (input.endsWith("credits")) {
            repository = creditRepository;
        }
        else if (input.contains(" is ")) {
            repository = symbolRepository;
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
