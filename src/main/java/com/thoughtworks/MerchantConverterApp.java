package com.thoughtworks;

import com.thoughtworks.parser.InputParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import static java.nio.file.Paths.get;

import static java.nio.file.Files.readAllBytes;

public class MerchantConverterApp {
    private static InputParser inputParser = new InputParser();

    public static void main(String[] args) {
        String input = parseInputFromFilePath(args[0]);
        processInput(input);
    }

    public static String parseInputFromFilePath(String filePath) {
        try {
            return new String(readAllBytes(get(filePath)));
        }
        catch (IOException e) {
            try {
                return attemptToLoadFileFromResource(filePath);
            }
            catch (URISyntaxException e1) {
                e1.printStackTrace();
            }
            catch (IOException e1) {
                e1.printStackTrace();
            }

            System.out.println("Could not load file from path: " + filePath);
            return null;
        }
    }

    private static String attemptToLoadFileFromResource(String filePath) throws URISyntaxException, IOException {
        URL url = Thread.currentThread().getContextClassLoader().getResource(filePath);
        return new String(readAllBytes(get(url.toURI())));
    }

    public static void processInput(String input) {
        inputParser.parse(input);
    }

    public void setInputParser(InputParser inputParser) {
        MerchantConverterApp.inputParser = inputParser;
    }
}
