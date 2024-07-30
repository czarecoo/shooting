package com.czareg.shooting.processing;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class SeriesFinder {

    public static List<String> find(@NonNull List<String> lines) {
        LinesValidator.validate(lines);
        return BlockFactory.create(lines)
                .stream()
                .map(BlockProcessor::process)
                .toList();
    }
}
