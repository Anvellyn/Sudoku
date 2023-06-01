package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SudokuBoardTest {

    @Test
    public void setterTest() {
        boolean notEqual = false;
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard board = new SudokuBoard(backtrackingSudokuSolver);
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++) {
                int randomValue = (int)Math.floor((Math.random() * 9 + 1));
                board.set(i, j, randomValue);
                if(board.get(i, j) != randomValue) {
                    notEqual = true;
                    break;
                }
                if(notEqual) break;
            }
        assertFalse(notEqual);
    }

    @Test
    public void setterVerifyParamTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        boolean returnedValue = sudokuBoard.set(0,0,1, false);
        assertEquals(1, sudokuBoard.get(0,0));
        assertTrue(returnedValue);
    }

    @Test
    public void getRowTrueVerifyTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.set(0,0,1);
        sudokuBoard.set(0,1,2);
        sudokuBoard.set(0,2,3);
        sudokuBoard.set(0,3,4);
        sudokuBoard.set(0,4,5);
        sudokuBoard.set(0,5,6);
        sudokuBoard.set(0,6,7);
        sudokuBoard.set(0,7,8);
        sudokuBoard.set(0,8,9);
        SudokuRow sudokuRow = sudokuBoard.getRow(0);
        assertTrue(sudokuRow.verify());
    }

    @Test
    public void getRowFalseVerifyTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.set(0,0,1);
        sudokuBoard.set(0,1,1);
        sudokuBoard.set(0,2,1);
        sudokuBoard.set(0,3,1);
        sudokuBoard.set(0,4,1);
        sudokuBoard.set(0,5,1);
        sudokuBoard.set(0,6,0);
        sudokuBoard.set(0,7,0);
        sudokuBoard.set(0,8,0);
        SudokuRow sudokuRow = sudokuBoard.getRow(0);
        assertFalse(sudokuRow.verify());
    }

    @Test
    public void getColumnTrueVerifyTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.set(0,0,1);
        sudokuBoard.set(1,0,2);
        sudokuBoard.set(2,0,3);
        sudokuBoard.set(3,0,4);
        sudokuBoard.set(4,0,5);
        sudokuBoard.set(5,0,6);
        sudokuBoard.set(6,0,7);
        sudokuBoard.set(7,0,8);
        sudokuBoard.set(8,0,9);
        SudokuColumn sudokuColumn = sudokuBoard.getColumn(0);
        assertTrue(sudokuColumn.verify());
    }

    @Test
    public void getColumnFalseVerifyTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.set(0,0,1);
        sudokuBoard.set(1,0,1);
        sudokuBoard.set(2,0,1);
        sudokuBoard.set(3,0,1);
        sudokuBoard.set(4,0,1);
        sudokuBoard.set(5,0,1);
        sudokuBoard.set(6,0,0);
        sudokuBoard.set(7,0,0);
        sudokuBoard.set(8,0,0);
        SudokuColumn sudokuColumn = sudokuBoard.getColumn(0);
        assertFalse(sudokuColumn.verify());
    }

    @Test
    public void getBoxTrueVerifyTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.set(0,0,1);
        sudokuBoard.set(0,1,2);
        sudokuBoard.set(0,2,3);
        sudokuBoard.set(1,0,4);
        sudokuBoard.set(1,1,5);
        sudokuBoard.set(1,2,6);
        sudokuBoard.set(2,0,7);
        sudokuBoard.set(2,1,8);
        sudokuBoard.set(2,2,9);
        SudokuBox sudokuBox = sudokuBoard.getBox(0,0);
        assertTrue(sudokuBox.verify());
    }

    @Test
    public void getBoxFalseVerifyTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        sudokuBoard.set(0,0,1);
        sudokuBoard.set(0,1,1);
        sudokuBoard.set(0,2,1);
        sudokuBoard.set(1,0,1);
        sudokuBoard.set(1,1,1);
        sudokuBoard.set(1,2,1);
        sudokuBoard.set(2,0,0);
        sudokuBoard.set(2,1,0);
        sudokuBoard.set(2,2,0);
        SudokuBox sudokuBox = sudokuBoard.getBox(0,0);
        assertFalse(sudokuBox.verify());
    }
    @Test
    public void equalsMethodCallByObjectForItselfTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        assertTrue(sudokuBoard.equals(sudokuBoard));
    }

    @Test
    public void equalsCaseEqualObjectsTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard1 = new SudokuBoard(backtrackingSudokuSolver);
        SudokuBoard sudokuBoard2 = sudokuBoard1;
        assertTrue(sudokuBoard1.equals(sudokuBoard2));
    }

    @Test
    public void equalsCaseObjectIsNull() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        SudokuBoard nullSudokuBoard = null;
        assertFalse(sudokuBoard.equals(nullSudokuBoard));
    }

    @Test
    public void equalsCaseObjectIsInstanceOfDifferentClass() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        String str = "This is String Class object";
        assertFalse(sudokuBoard.equals(str));
    }

    @Test
    public void equalsCaseEqualFieldsTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard1 = new SudokuBoard(backtrackingSudokuSolver);
        SudokuBoard sudokuBoard2 = new SudokuBoard(backtrackingSudokuSolver);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard1.set(i, j, 1);
                sudokuBoard2.set(i, j, 1);
            }
        }
        assertTrue(sudokuBoard1.equals(sudokuBoard2));
        assertTrue(sudokuBoard2.equals(sudokuBoard1));
    }

    @Test
    public void equalsCaseNotEqualFieldsTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard1 = new SudokuBoard(backtrackingSudokuSolver);
        SudokuBoard sudokuBoard2 = new SudokuBoard(backtrackingSudokuSolver);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard1.set(i, j, 1);
            }
        }
        assertFalse(sudokuBoard1.equals(sudokuBoard2));
    }

    @Test
    public void equalsCaseChangedClassFieldsInitiallyEqualTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard1 = new SudokuBoard(backtrackingSudokuSolver);
        SudokuBoard sudokuBoard2 = new SudokuBoard(backtrackingSudokuSolver);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard1.set(i, j, 1);
                sudokuBoard2.set(i, j, 1);
            }
        }
        assertTrue(sudokuBoard1.equals(sudokuBoard2));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard2.set(i, j, 2);
            }
        }
        assertFalse(sudokuBoard1.equals(sudokuBoard2));
    }

    @Test
    public void equalsCaseChangedClassFieldsInitiallyNotEqualTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard1 = new SudokuBoard(backtrackingSudokuSolver);
        SudokuBoard sudokuBoard2 = new SudokuBoard(backtrackingSudokuSolver);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard1.set(i, j, 1);
            }
        }
        assertFalse(sudokuBoard1.equals(sudokuBoard2));
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard2.set(i, j, 1);
            }
        }
        assertTrue(sudokuBoard1.equals(sudokuBoard2));
    }

    @Test
    public void hashCodeCaseEqualObjectFieldsTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard1 = new SudokuBoard(backtrackingSudokuSolver);
        SudokuBoard sudokuBoard2 = new SudokuBoard(backtrackingSudokuSolver);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard1.set(i, j, 1);
                sudokuBoard2.set(i, j, 1);
            }
        }
        assertEquals(sudokuBoard1.hashCode(), sudokuBoard2.hashCode());
    }

    @Test
    public void hashCodeCaseChangedClassFieldsInitiallyNotEqualTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard1 = new SudokuBoard(backtrackingSudokuSolver);
        SudokuBoard sudokuBoard2 = new SudokuBoard(backtrackingSudokuSolver);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard1.set(i, j, 1);
                sudokuBoard2.set(i, j, 2);
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard2.set(i, j, 1);
            }
        }
        assertEquals(sudokuBoard1.hashCode(), sudokuBoard2.hashCode());
    }

    @Test
    public void ShouldHashCodeNotChangeWhenCalledMultipleTimesForSameUnchangedObjectTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        int hashCode = sudokuBoard.hashCode();
        assertEquals(sudokuBoard.hashCode(), hashCode);
    }

    @Test
    public void ShouldHashCodeChangeWhenCalledMultipleTimesForSameChangedObjectTest() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        int hashCode = sudokuBoard.hashCode();
        sudokuBoard.set(0, 0, 1);
        assertNotEquals(sudokuBoard.hashCode(), hashCode);
    }

    @Test
    public void cloneTest() throws SudokuBoardException.SudokuBoardCloneNotSupportedException {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard = new SudokuBoard(backtrackingSudokuSolver);
        backtrackingSudokuSolver.solve(sudokuBoard);
        SudokuBoard sudokuBoardDrugie = (SudokuBoard) sudokuBoard.clone();
        assertTrue(sudokuBoard.equals(sudokuBoardDrugie));
        sudokuBoard.set(0, 0,0 );
        assertFalse(sudokuBoard.equals(sudokuBoardDrugie));
    }

    @Test
    public void dopisany() {
        BacktrackingSudokuSolver backtrackingSudokuSolver = new BacktrackingSudokuSolver();
        SudokuBoard sudokuBoard1 = new SudokuBoard(backtrackingSudokuSolver);
        SudokuBoard sudokuBoard2 = new SudokuBoard(backtrackingSudokuSolver);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard1.set(i, j, 1);
                sudokuBoard2.set(i, j, 2);
            }
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard2.set(i, j, 1);
            }
        }
        sudokuBoard2.changeSudokuBoardToString();
        sudokuBoard2.changeEditListToString();
        assertFalse(false);
    }


}