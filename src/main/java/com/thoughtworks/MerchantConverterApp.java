package com.thoughtworks;

import com.thoughtworks.parser.InputParser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MerchantConverterApp {
    private static InputParser inputParser = new InputParser();

    public static void main(String[] args) {
        String input = parseInputFromFilePath(args[0]);
        log(processInput(input));
    }

    public static String parseInputFromFilePath(String filePath) {
        URL url = Thread.currentThread().getContextClassLoader().getResource(filePath);

        try {
            return new String(Files.readAllBytes(Paths.get(url.toURI())));
        } catch (IOException e) {
            System.out.println("Could not load file from path: " + filePath);
            return null;
        } catch (URISyntaxException e) {
            System.out.println("Could not load file from path: " + filePath);
            return null;
        }
    }

    public static String processInput(String input) {
        return inputParser.parse(input);
    }

    public static void log(String input) {
        System.out.println(input);
    }

    public void setInputParser(InputParser inputParser) {
        this.inputParser = inputParser;
    }
}