package com.example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BacktrackingSudokuSolver implements SudokuSolver, Serializable {
    @Override
    public void solve(SudokuBoard sudokuBoard) {
        generateFirstRow(sudokuBoard);
        fillingBoardByBacktracking(1,0, sudokuBoard);
    }

    private void generateFirstRow(SudokuBoard sudokuBoard) {
        Integer[] firstRow = { 1, 2, 3, 4, 5, 6, 7, 8, 9};

        List<Integer> list = Arrays.asList(firstRow);

        Collections.shuffle(list);
        list.toArray(firstRow);
        for (int i = 0; i < 9; i++) {
            sudokuBoard.set(0,i,firstRow[i]);
        }

    }

    private boolean fillingBoardByBacktracking(int row, int column, SudokuBoard sudokuBoard) {
        if (row == 9) {
            return true;
        }
        int newRow = row;
        for (int i = 1; i < 10; i++) {
            if (sudokuBoard.set(row,column,i,true)) {
                if ((column + 1) % 9 == 0) {
                    newRow = row + 1;
                }
                if (fillingBoardByBacktracking(newRow, (column + 1) % 9, sudokuBoard)) {
                    return true;
                }
            }
        }
        sudokuBoard.set(row, column, 0);
        return false;
    }
}