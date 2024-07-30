package com.czareg.shooting.processing;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SeriesFinderTest {

    @Test
    void shouldReturnExpectedValuesForExample() {
        String input = """
                2
                4 4
                2 4
                3 4
                1 3
                1 4
                5 5
                1 5
                2 4
                3 4
                2 4
                2 3
                """;
        List<String> inputLines = input.lines().toList();

        List<String> result = SeriesFinder.find(inputLines);

        List<String> expected = List.of("2 3 1 4", "NO");
        assertEquals(expected, result);
    }

    @Test
    void shouldReturnExpectedValueWhenGivenOneBigBlock() {
        String input = """
                1
                10 10
                1 2
                3 4
                5 6
                7 8
                9 10
                1 3
                5 7
                9 2
                4 6
                8 10
                """;
        List<String> inputLines = input.lines().toList();

        List<String> result = SeriesFinder.find(inputLines);

        List<String> expected = List.of("1 4 5 8 9 3 7 2 6 10");
        assertEquals(expected, result);
    }

    @Test
    void shouldReturnExpectedValueWhenGivenSmallestPossible() {
        String input = """
                1
                2 2
                2 1
                1 2
                """;
        List<String> inputLines = input.lines().toList();

        List<String> result = SeriesFinder.find(inputLines);

        List<String> expected = List.of("2 1");
        assertEquals(expected, result);
    }

    @Test
    void shouldReturnNoWhenGivenBlockWithOnlyTwoRowsAndManyColumns() {
        String input = """
                1
                2 5
                2 1
                1 2
                2 1
                1 2
                1 2
                """;
        List<String> inputLines = input.lines().toList();

        List<String> result = SeriesFinder.find(inputLines);

        List<String> expected = List.of("NO");
        assertEquals(expected, result);
    }

    @Test
    void shouldThrowWhenGivenInputWithZeroBlocks() {
        String input = """
                0
                """;
        List<String> inputLines = input.lines().toList();

        assertThrows(IllegalArgumentException.class, () -> SeriesFinder.find(inputLines));
    }

    @Test
    void shouldThrowWhenGivenInputWithSixBlocks() {
        String input = """
                6
                """;
        List<String> inputLines = input.lines().toList();

        assertThrows(IllegalArgumentException.class, () -> SeriesFinder.find(inputLines));
    }

    @Test
    void shouldThrowWhenGivenInputWithOneBlockWithZeroByZeroDimensions() {
        String input = """
                1
                0 0
                """;
        List<String> inputLines = input.lines().toList();

        assertThrows(IllegalArgumentException.class, () -> SeriesFinder.find(inputLines));
    }

    @Test
    void shouldThrowWhenGivenBlockWithWhitePositionsBiggerThanNumberOfRows() {
        String input = """
                1
                2 3
                3 2
                1 2
                1 3
                """;
        List<String> inputLines = input.lines().toList();

        assertThrows(IllegalArgumentException.class, () -> SeriesFinder.find(inputLines));
    }
}