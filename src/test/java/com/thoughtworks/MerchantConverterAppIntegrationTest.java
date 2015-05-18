package com.thoughtworks;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MerchantConverterAppIntegrationTest {
    private static final String FULL_INPUT_PATH =
            "/Users/akumadevil/IdeaProjects/thoughtworks-converter/src/test/resources/test-input.txt";
    private static final String OUTPUT_LINE_1 = "pish tegj glob glob is 42";
    private static final String OUTPUT_LINE_2 = "glob prok Silver is 68 Credits";
    private static final String OUTPUT_LINE_3 = "glob prok Gold is 57800 Credits";
    private static final String OUTPUT_LINE_4 = "glob prok Iron is 782 Credits";
    private static final String OUTPUT_LINE_5 = "I have no idea what you are talking about";
    private PrintStream mockPrintStream;

    @Before
    public void setUp() throws IOException, URISyntaxException {
        mockPrintStream = mock(PrintStream.class);
        System.setOut(mockPrintStream);

        MerchantConverterApp app = new MerchantConverterApp();
        MerchantConverterApp.main(new String[]{FULL_INPUT_PATH});
    }

    @After
    public void tearDown() {
        System.setOut(null);
    }

    @Test
    public void shouldOutputLine1() {
        verify(mockPrintStream).println(OUTPUT_LINE_1);
    }

    @Test
    public void shouldOutputLine2() {
        verify(mockPrintStream).println(OUTPUT_LINE_2);
    }

    @Test
    public void shouldOutputLine3() {
        verify(mockPrintStream).println(OUTPUT_LINE_3);
    }

    @Test
    public void shouldOutputLine4() {
        verify(mockPrintStream).println(OUTPUT_LINE_4);
    }

    @Test
    public void shouldOutputLine5() {
        verify(mockPrintStream).println(OUTPUT_LINE_5);
    }
}
