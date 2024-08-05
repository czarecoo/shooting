package com.czareg.shooting;

import com.czareg.shooting.file.FileOperations;
import com.czareg.shooting.processing.SeriesFinder;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> inputLines = FileOperations.readLinesFromFile();
        List<String> outputLines = SeriesFinder.find(inputLines);
        FileOperations.saveLinesToFile(outputLines);
    }
}