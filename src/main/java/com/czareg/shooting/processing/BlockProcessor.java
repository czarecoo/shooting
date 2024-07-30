package com.czareg.shooting.processing;

import lombok.experimental.UtilityClass;

import java.util.Arrays;
import java.util.stream.Collectors;

@UtilityClass
class BlockProcessor {

    static String process(Block block) {
        boolean[] usedRows = new boolean[block.getRows() + 1];
        int[] temporaryResult = new int[block.getColumns()];
        if (findValidSeries(0, block, usedRows, temporaryResult)) {
            return Arrays.stream(temporaryResult)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining(" "));
        }
        return "NO";
    }

    private static boolean findValidSeries(int column, Block block, boolean[] usedRows, int[] temporaryResult) {
        if (column == block.getColumns()) {
            return true; // All columns processed
        }

        for (int row : block.getWhitePositions()[column]) {
            if (!usedRows[row]) {
                usedRows[row] = true;
                temporaryResult[column] = row;

                if (findValidSeries(column + 1, block, usedRows, temporaryResult)) {
                    return true;
                }

                usedRows[row] = false; // Backtrack
            }
        }
        return false; // No valid series found for this configuration
    }
}
