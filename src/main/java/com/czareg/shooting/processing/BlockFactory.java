package com.czareg.shooting.processing;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
class BlockFactory {

    static List<Block> create(List<String> lines) {
        List<Block> blocks = new ArrayList<>();
        int numberOfBlocks = Integer.parseInt(lines.getFirst().trim());
        int lineIndex = 1;
        for (int block = 0; block < numberOfBlocks; block++) {
            int[] firstBlockLine = readTwoIntValuesFromLine(lines.get(lineIndex++));
            int rows = firstBlockLine[0];
            int columns = firstBlockLine[1];
            int[][] whitePositions = new int[columns][2];
            for (int column = 0; column < columns; column++) {
                whitePositions[column] = readTwoIntValuesFromLine(lines.get(lineIndex++));
            }
            blocks.add(new Block(rows, columns, whitePositions));
        }

        return blocks;
    }

    private static int[] readTwoIntValuesFromLine(String line) {
        String[] values = line.trim().split(" ");
        int firstValue = Integer.parseInt(values[0]);
        int secondValue = Integer.parseInt(values[1]);
        return new int[]{firstValue, secondValue};
    }
}
