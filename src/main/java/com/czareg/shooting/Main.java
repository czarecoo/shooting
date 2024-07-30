package com.czareg.shooting;

import com.czareg.shooting.file.FileOperations;
import com.czareg.shooting.processing.SeriesFinder;

import java.util.List;

/*
Welcome to the Annual Shooting Competition in Bajtocja. Each competitor shoots at a rectangular target.
The target is divided into r rows and c columns, consisting of r × c square fields. Each field of the target is painted either black or white.
It is known that each column of the target has exactly two white fields and r - 2 black fields.
The rows of the target are numbered from top to bottom with consecutive natural numbers 1, ..., r.
The columns of the target are numbered from left to right with numbers 1, ..., c. Each competitor shoots a series of c shots.
A series of c shots is correct if exactly one white field is hit in each column and there is no row without a hit white field.
Help the competitor find a correct series of shots, if such a series exists.

Write a program that:
Reads the number of data blocks from the text file SHO.IN. Each block contains a complete description of a target.
Then, for each block:
Reads the size of the target and the distribution of white fields on the target.
Checks if there is a valid series of shots for the target, if so, finds one.
Writes the result to the text file SHO.OUT.

Input
The first line of the data file SHO.IN contains the number of data blocks (target descriptions) 1≤x≤5.
The next lines describe x blocks. The description of the first block starts on the second line of the input file,
and the description of each subsequent block starts on the line immediately following the end of the previous block's description.

The description of each block (target) starts with a line containing two integers r and c (2≤r≤c≤1000) separated by a single space.
These numbers represent the number of rows and columns of the target, respectively.
Each of the next c lines of the block description contains two integers separated by a single space.
The numbers in the (i + 1)-th line of the block (1≤i≤c) represent the positions (i.e., row numbers on the board) of
the white fields in the i-th column of the target.

Output
For the i-th block, 1≤i≤x, your program should write in the i-th line of the output file SHO.OUT a sequence of c row numbers
(separated by single spaces) forming a valid series of shots to the white fields of the successive columns 1, 2, ..., c.
If no valid series exists for the given target, the word "NO" should be written in the i-th line of the output file SHO.OUT.
 */
public class Main {

    public static void main(String[] args) {
        List<String> inputLines = FileOperations.readLinesFromFile();
        List<String> outputLines = SeriesFinder.find(inputLines);
        FileOperations.saveLinesToFile(outputLines);
    }
}