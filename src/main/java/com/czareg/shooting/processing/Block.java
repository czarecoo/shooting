package com.czareg.shooting.processing;

import lombok.Value;

@Value
class Block {

    int rows;
    int columns;
    int[][] whitePositions;
}
