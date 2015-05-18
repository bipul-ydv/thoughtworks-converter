package com.thoughtworks.writer;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

public class UnknownWriterTest {
    private static final String TEST_INPUT = "how much wood could a woodchuck chuck if a woodchuck could chuck wood ?";
    private static final String UNKNOWN_MESSAGE = "I have no idea what you are talking about";
    private UnknownWriter writer;
    private PrintStream mockPrintStream;

    @Before
    public void setUp() {
        writer = new UnknownWriter();
        mockPrintStream = mock(PrintStream.class);
        System.setOut(mockPrintStream);
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }

    @Test
    public void shouldOutputUnknownMessage() {
        writer.process(TEST_INPUT);
        verify(mockPrintStream).println(UNKNOWN_MESSAGE);
    }
}
