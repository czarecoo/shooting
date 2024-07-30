package com.czareg.shooting.file;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@UtilityClass
public class FileOperations {

    private static final String INPUT_FILE_NAME = "SHO.IN";
    private static final String OUTPUT_FILE_NAME = "SHO.OUT";

    public static List<String> readLinesFromFile() {
        try {
            Path path = FilePathProvider.provide().resolve(INPUT_FILE_NAME);
            return Files.readAllLines(path);
        } catch (IOException | URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

    public static void saveLinesToFile(@NonNull List<String> outputLines) {
        try {
            Path path = FilePathProvider.provide().resolve(OUTPUT_FILE_NAME);
            Files.write(path, outputLines);
        } catch (IOException | URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }
}
