package com.thoughtworks.parser;

import com.thoughtworks.reader.CreditReader;
import com.thoughtworks.reader.SymbolReader;
import com.thoughtworks.writer.CreditWriter;
import com.thoughtworks.writer.SymbolWriter;
import com.thoughtworks.writer.UnknownWriter;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class InputParserTest {
    private static final String TEST_INPUT_READ_SYMBOL = "prok is V";
    private static final String TEST_INPUT_READ_CREDIT = "glob prok Gold is 57800 Credits";
    private static final String TEST_INPUT_WRITE_SYMBOL = "how much is pish tegj glob glob ?";
    private static final String TEST_INPUT_WRITE_CREDIT = "how many Credits is glob prok Gold ?";
    private static final String TEST_INPUT_WRITE_UNKNOWN = "how much wood could a woodchuck chuck ?";
    private static final String TEST_INPUT_LINE = "foo";
    private static final String TEST_INPUT_FILE = TEST_INPUT_LINE + "\n" + TEST_INPUT_LINE + "\n" + TEST_INPUT_LINE;
    private InputParser parser;
    private SymbolReader symbolReader;
    private CreditReader creditReader;
    private SymbolWriter symbolWriter;
    private CreditWriter creditWriter;
    private UnknownWriter unknownWriter;

    @Before
    public void setUp() {
        parser = new InputParser();

        symbolReader = mock(SymbolReader.class);
        creditReader = mock(CreditReader.class);
        symbolWriter = mock(SymbolWriter.class);
        creditWriter = mock(CreditWriter.class);
        unknownWriter = mock(UnknownWriter.class);

        parser.setSymbolReader(symbolReader);
        parser.setCreditReader(creditReader);
        parser.setSymbolWriter(symbolWriter);
        parser.setCreditWriter(creditWriter);
        parser.setUnknownWriter(unknownWriter);
    }

    @Test
    public void shouldParseReadSymbol() {
        parser.parseSingleLine(TEST_INPUT_READ_SYMBOL);
        verify(symbolReader).input(TEST_INPUT_READ_SYMBOL);
    }

    @Test
    public void shouldParseReadCredit() {
        parser.parseSingleLine(TEST_INPUT_READ_CREDIT);
        verify(creditReader).input(TEST_INPUT_READ_CREDIT);
    }

    @Test
    public void shouldParseWriteSymbol() {
        parser.parseSingleLine(TEST_INPUT_WRITE_SYMBOL);
        verify(symbolWriter).output(TEST_INPUT_WRITE_SYMBOL);
    }

    @Test
    public void shouldParseWriteCredit() {
        parser.parseSingleLine(TEST_INPUT_WRITE_CREDIT);
        verify(creditWriter).output(TEST_INPUT_WRITE_CREDIT);
    }

    @Test
    public void shouldParseWriteUnknown() {
        parser.parseSingleLine(TEST_INPUT_WRITE_UNKNOWN);
        verify(unknownWriter).output(TEST_INPUT_WRITE_UNKNOWN);
    }

    @Test
    public void shouldParseMultipleLines() {
        parser.parse(TEST_INPUT_FILE);
        verify(unknownWriter, times(3)).output(TEST_INPUT_LINE);
    }
}

