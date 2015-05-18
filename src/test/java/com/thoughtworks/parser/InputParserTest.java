package com.thoughtworks.parser;

import com.thoughtworks.repository.CreditRepository;
import com.thoughtworks.repository.SymbolRepository;
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
    private SymbolRepository symbolRepository;
    private CreditRepository creditRepository;
    private SymbolWriter symbolWriter;
    private CreditWriter creditWriter;
    private UnknownWriter unknownWriter;

    @Before
    public void setUp() {
        parser = new InputParser();

        symbolRepository = mock(SymbolRepository.class);
        creditRepository = mock(CreditRepository.class);
        symbolWriter = mock(SymbolWriter.class);
        creditWriter = mock(CreditWriter.class);
        unknownWriter = mock(UnknownWriter.class);

        parser.setSymbolRepository(symbolRepository);
        parser.setCreditRepository(creditRepository);
        parser.setSymbolWriter(symbolWriter);
        parser.setCreditWriter(creditWriter);
        parser.setUnknownWriter(unknownWriter);
    }

    @Test
    public void shouldAssignSymbolToRepository() {
        parser.normaliseAndParseSingleLine(TEST_INPUT_READ_SYMBOL);
        verify(symbolRepository).put(normalise(TEST_INPUT_READ_SYMBOL));
    }

    @Test
    public void shouldAssignCreditToRepository() {
        parser.normaliseAndParseSingleLine(TEST_INPUT_READ_CREDIT);
        verify(creditRepository).put(normalise(TEST_INPUT_READ_CREDIT));
    }

    @Test
    public void shouldOutputSymbolWriter() {
        parser.normaliseAndParseSingleLine(TEST_INPUT_WRITE_SYMBOL);
        verify(symbolWriter).process(normalise(TEST_INPUT_WRITE_SYMBOL));
    }

    @Test
    public void shouldOutputCreditWriter() {
        parser.normaliseAndParseSingleLine(TEST_INPUT_WRITE_CREDIT);
        verify(creditWriter).process(normalise(TEST_INPUT_WRITE_CREDIT));
    }

    @Test
    public void shouldOutputUnknownWriter() {
        parser.normaliseAndParseSingleLine(TEST_INPUT_WRITE_UNKNOWN);
        verify(unknownWriter).process(normalise(TEST_INPUT_WRITE_UNKNOWN));
    }

    @Test
    public void shouldParseMultipleLines() {
        parser.parse(TEST_INPUT_FILE);
        verify(unknownWriter, times(3)).process(normalise(TEST_INPUT_LINE));
    }

    private String normalise(String input) {
        return input.toLowerCase().trim();
    }
}

