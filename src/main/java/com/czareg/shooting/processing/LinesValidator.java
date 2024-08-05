package com.czareg.shooting.processing;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
class LinesValidator {

    static void validate(List<String> inputLines) {
        validateLinesNotEmpty(inputLines);
        int numberOfBlocks = parseIntOrThrow(inputLines.getFirst());
        validateNumberOfBlocks(numberOfBlocks);
        int lineIndex = 1;
        for (int block = 0; block < numberOfBlocks; block++) {
            int[] firstLine = readTwoIntValuesFromLine(lineIndex++, inputLines);
            int rows = firstLine[0];
            int columns = firstLine[1];
            validateRowsAndColumns(rows, columns);
            for (int column = 0; column < columns; column++) {
                int[] line = readTwoIntValuesFromLine(lineIndex++, inputLines);
                validateWhitePositions(line, rows);
            }
        }
        validateAllDataRead(lineIndex, inputLines);
    }

    private static int[] readTwoIntValuesFromLine(int lineIndex, List<String> inputLines) {
        if (lineIndex >= inputLines.size()) {
            throw new IllegalArgumentException("Line: %s is missing".formatted(lineIndex + 1));
        }
        String[] line = inputLines.get(lineIndex).trim().split(" ");
        if (line.length != 2) {
            throw new IllegalArgumentException("Line: %s must contain two values".formatted(lineIndex + 1));
        }
        int firstValue = parseIntOrThrow(line[0]);
        int secondValue = parseIntOrThrow(line[1]);
        return new int[]{firstValue, secondValue};
    }

    private static void validateLinesNotEmpty(List<String> inputLines) {
        if (inputLines.isEmpty()) {
            throw new IllegalArgumentException("Input file is empty");
        }
    }

    private static void validateNumberOfBlocks(int numberOfBlocks) {
        if (numberOfBlocks < 1 || numberOfBlocks > 5) {
            throw new IllegalArgumentException("Number of blocks must satisfy: (1 <= numberOfBlocks <= 5)");
        }
    }

    private static void validateRowsAndColumns(int rows, int columns) {
        if (rows < 2 || columns > 1000 || rows > columns) {
            throw new IllegalArgumentException("Rows and columns must satisfy (2 <= rows <= columns <= 1000)");
        }
    }

    private static void validateWhitePositions(int[] lineValues, int rows) {
        int firstWhitePosition = lineValues[0];
        int secondWhitePosition = lineValues[1];
        if (firstWhitePosition < 1 || firstWhitePosition > rows
                || secondWhitePosition < 1 || secondWhitePosition > rows
                || firstWhitePosition == secondWhitePosition) {
            throw new IllegalArgumentException("White positions must satisfy: (1 <= white position <= rows) and must be different from each other");
        }
    }

    private static void validateAllDataRead(int lineIndex, List<String> inputLines) {
        if (lineIndex != inputLines.size()) {
            throw new IllegalArgumentException("Unexpected unread line: %s".formatted(lineIndex + 1));
        }
    }

    private static int parseIntOrThrow(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Failed to parse string to int", e);
        }
    }
}
