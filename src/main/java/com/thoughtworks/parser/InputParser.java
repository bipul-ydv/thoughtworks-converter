package com.thoughtworks.parser;

import com.thoughtworks.factory.RepositoryFactory;
import com.thoughtworks.factory.WriterFactory;

public class InputParser {
    private static final String WRITER_LINE_PREFIX = "how ";

    public void parse(String input) {
        for (String line : input.split("\\r?\\n")) {
            parseSingleLine(line);
        }
    }

    public void parseSingleLine(String input) {
        if (input.startsWith(WRITER_LINE_PREFIX)) {
            WriterFactory.getWriter(input).process(input);
        }
        else {
            RepositoryFactory.getRepository(input).put(input);;
        }
    }
}
