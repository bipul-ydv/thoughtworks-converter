package com.thoughtworks;

import com.thoughtworks.parser.InputParser;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MerchantConverterAppTest {
    private static final String TEST_INPUT_PATH = "test-input.txt";
    private static final String TEST_OUTPUT_PATH = "test-output.txt";
    private static final String TEST_DATA = "test-data";
    private MerchantConverterApp app;
    private String testInput;
    private String testOutput;

    @Before
    public void setUp() throws IOException, URISyntaxException {
        app = new MerchantConverterApp();

        URL url = Thread.currentThread().getContextClassLoader().getResource(TEST_INPUT_PATH);
        testInput = new String(Files.readAllBytes(Paths.get(url.toURI())));

        url = Thread.currentThread().getContextClassLoader().getResource(TEST_OUTPUT_PATH);
        testOutput = new String(Files.readAllBytes(Paths.get(url.toURI())));
    }

    @Test
    public void shouldParseInputFromFile() {
        String input = MerchantConverterApp.parseInputFromFilePath(TEST_INPUT_PATH);
        assertThat(input, is(equalTo(testInput)));
    }

    @Test
    public void shouldCallParser() {
        InputParser mockInputParser = mock(InputParser.class);
        app.setInputParser(mockInputParser);
        MerchantConverterApp.processInput(TEST_DATA);
        verify(mockInputParser).parse(TEST_DATA);
    }
}
