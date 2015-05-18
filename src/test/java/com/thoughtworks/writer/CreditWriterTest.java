package com.thoughtworks.writer;

import com.thoughtworks.repository.CreditRepository;
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
    private CreditRepository creditRepository;

    @Before
    public void setUp() {
        writer = new CreditWriter();

        mockPrintStream = mock(PrintStream.class);
        symbolRepository = mock(SymbolRepository.class);
        creditRepository = mock(CreditRepository.class);

        when(symbolRepository.get("one")).thenReturn("I");
        when(symbolRepository.get("ten")).thenReturn("X");
        when(symbolRepository.get("fifty")).thenReturn("L");
        when(creditRepository.get("Foo")).thenReturn("2");

        writer.setSymbolRepository(symbolRepository);
        writer.setCreditRepository(creditRepository);

        System.setOut(mockPrintStream);
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }

    @Test
    public void shouldOutputNoMultiplierCredits() {
        writer.process("how many Credits is ten Foo ?");
        verify(mockPrintStream).println("ten Foo is 20 Credits");
    }

    @Test
    public void shouldOutputMultiplierCredits() {
        writer.process("how many Credits is ten fifty Foo ?");
        verify(mockPrintStream).println("ten fifty Foo is 80 Credits");
    }
}
