package com.czareg.shooting.processing;

import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
class LinesValidator {

    static void validate(List<String> lines) {
        int numberOfBlocks = parseIntOrThrow(lines.getFirst());
        validateNumberOfBlocks(numberOfBlocks);
        int lineIndex = 1;
        for (int block = 0; block < numberOfBlocks; block++) {
            int[] firstLine = readTwoIntValuesFromLine(lineIndex++, lines);
            int rows = firstLine[0];
            int columns = firstLine[1];
            validateRowsAndColumns(rows, columns);
            for (int column = 0; column < columns; column++) {
                int[] line = readTwoIntValuesFromLine(lineIndex++, lines);
                validateWhitePositions(line, rows);
            }
        }
        validateAllDataRead(lineIndex, lines);
    }

    private static int[] readTwoIntValuesFromLine(int lineIndex, List<String> lines) {
        if (lineIndex >= lines.size()) {
            throw new IllegalArgumentException("Line: %s is missing".formatted(lineIndex + 1));
        }
        String[] line = lines.get(lineIndex).trim().split(" ");
        if (line.length != 2) {
            throw new IllegalArgumentException("Line: %s must contain two values".formatted(lineIndex + 1));
        }
        int firstValue = parseIntOrThrow(line[0]);
        int secondValue = parseIntOrThrow(line[1]);
        return new int[]{firstValue, secondValue};
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

    private static void validateWhitePositions(int[] line, int rows) {
        int firstWhitePosition = line[0];
        int secondWhitePosition = line[1];
        if (firstWhitePosition < 1 || firstWhitePosition > rows
                || secondWhitePosition < 1 || secondWhitePosition > rows
                || firstWhitePosition == secondWhitePosition) {
            throw new IllegalArgumentException("White positions must satisfy: (1 <= white position <= rows) and must be different from each other");
        }
    }

    private static void validateAllDataRead(int lineIndex, List<String> lines) {
        if (lineIndex != lines.size()) {
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
