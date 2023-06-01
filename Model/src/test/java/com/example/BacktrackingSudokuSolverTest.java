package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BacktrackingSudokuSolverTest {

    private boolean checkIfCorrectTest(int row, int column, int number, SudokuBoard sudokuBoard) {
        if (!checkRow(row, column, number, sudokuBoard)) {
            return false;
        }
        if (!checkColumn(row, column, number, sudokuBoard)) {
            return false;
        }
        if (!check3x3(row, column, number, sudokuBoard)) {
            return false;
        }
        return true;
    }

    private boolean checkRow(int row, int column, int number, SudokuBoard sudokuBoard) {
        for (int i = 0; i < 9; i++) {
            if (number == sudokuBoard.get(row, i) && i != column) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int row, int column, int number, SudokuBoard sudokuBoard) {
        for (int i = 0; i < 9; i++) {
            if (number == sudokuBoard.get(i, column) && i != row) {
                return false;
            }
        }
        return true;
    }

    private boolean check3x3(int row, int column, int number, SudokuBoard sudokuBoard) {
        int newRow = row / 3;
        int newColumn = column / 3;
        for (int i = newRow * 3; i < (newRow * 3) + 3;i++) {
            for (int j = newColumn * 3; j < (newColumn * 3) + 3; j++) {
                if (number == sudokuBoard.get(i, j) && (i != row && j != column)) {
                    return false;
                }
            }
        }
        return true;
    }



    @Test
    public void czyRozneTest() {
        boolean czyIdentyczne = true;
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard jeden = new SudokuBoard(backtrackingSudokuSolver);
        SudokuBoard drugi = new SudokuBoard(backtrackingSudokuSolver);
        jeden.solveGame();
        drugi.solveGame();
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                if (jeden.get(i, j) != drugi.get(i,j))
                {
                    czyIdentyczne = false;
                    break;
                }
                if(czyIdentyczne==false) break;
            }
        }
        assertFalse(czyIdentyczne);
    }

    @Test
    public void czyPoprawneSudokuTest()
    {
        boolean czyPoprawne = true;
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.solveGame();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!checkIfCorrectTest(i,j,sudokuBoard.get(i,j), sudokuBoard)){
                    czyPoprawne = false;
                    break;
                }
            }
            if(czyPoprawne == false) break;
        }
        assertTrue(czyPoprawne);
    }
}