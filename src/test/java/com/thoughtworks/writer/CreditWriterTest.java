package com.thoughtworks.writer;

import com.thoughtworks.repository.SymbolRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class CreditWriterTest {
    private CreditWriter writer;
    private PrintStream mockPrintStream;
    private SymbolRepository symbolRepository;

    @Before
    public void setUp() {
        writer = new CreditWriter();

        mockPrintStream = mock(PrintStream.class);
        symbolRepository = mock(SymbolRepository.class);

        when(symbolRepository.get("one")).thenReturn("I");
        when(symbolRepository.get("ten")).thenReturn("X");
        when(symbolRepository.get("fifty")).thenReturn("L");

        writer.setRepository(symbolRepository);

        System.setOut(mockPrintStream);
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }

    @Test
    public void shouldOutputSumSingleVariable() {
        writer.process("how many Credits is glob prok Gold ?");
        verify(mockPrintStream).println("glob prok Gold is 57800 Credits");
    }
}
