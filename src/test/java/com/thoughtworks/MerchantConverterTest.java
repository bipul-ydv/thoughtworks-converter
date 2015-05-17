package com.thoughtworks;

import com.thoughtworks.processor.ConversionProcessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MerchantConverterTest {
    private static final String TEST_INPUT_PATH = "test-input.txt";
    private static final String TEST_OUTPUT_PATH = "test-output.txt";
    private MerchantConverter converter;
    private String testInput;
    private String testOutput;

    @Before
    public void setUp() throws IOException, URISyntaxException {
        converter = new MerchantConverter();

        URL url = Thread.currentThread().getContextClassLoader().getResource(TEST_INPUT_PATH);
        testInput = new String(Files.readAllBytes(Paths.get(url.toURI())));

        url = Thread.currentThread().getContextClassLoader().getResource(TEST_OUTPUT_PATH);
        testOutput = new String(Files.readAllBytes(Paths.get(url.toURI())));
    }

    @Test
    public void shouldParseInputFromFile() {
        String input = converter.parseInputFromFilePath(TEST_INPUT_PATH);
        assertThat(input, is(equalTo(testInput)));
    }

    @Test
    public void shouldProcessInputToExpectedOutput() {
        String output = converter.processInput(testInput);
        assertThat(output, is(equalTo(testOutput)));
    }

    @Test
    public void shouldPrintOutput() {
        String testData = "foo";
        PrintStream mockPrintStream = mock(PrintStream.class);
        System.setOut(mockPrintStream);
        ConversionProcessor mockProcessor = mock(ConversionProcessor.class);
        when(mockProcessor.processInput(TEST_INPUT_PATH)).thenReturn(testData);
        converter.main(new String[]{testData});
        verify(mockPrintStream).println(testData);
        System.setOut(null);
    }
}
