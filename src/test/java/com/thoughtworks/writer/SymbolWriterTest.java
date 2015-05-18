package com.thoughtworks.writer;

import com.thoughtworks.repository.SymbolRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class SymbolWriterTest {
    private SymbolWriter writer;
    private PrintStream mockPrintStream;
    private SymbolRepository symbolRepository;

    @Before
    public void setUp() {
        writer = new SymbolWriter();

        mockPrintStream = mock(PrintStream.class);
        symbolRepository = mock(SymbolRepository.class);

        when(symbolRepository.get("one")).thenReturn("I");
        when(symbolRepository.get("ten")).thenReturn("X");
        when(symbolRepository.get("fifty")).thenReturn("L");

        writer.setSymbolRepository(symbolRepository);

        System.setOut(mockPrintStream);
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }

    @Test
    public void shouldOutputSingleVariableSum() {
        writer.process("how much is ten ?");
        verify(mockPrintStream).println("ten is 10");
    }

    @Test
    public void shouldOutputMultipleVariableSum() {
        writer.process("how much is ten fifty one one ?");
        verify(mockPrintStream).println("ten fifty one one is 42");
    }
}
