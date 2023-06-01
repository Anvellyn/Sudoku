package com.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuRowTest {
    private List<SudokuField> createList() {
        List<SudokuField> list = new ArrayList<>();
        list.add(new SudokuField(1));
        list.add(new SudokuField(2));
        list.add(new SudokuField(3));
        list.add(new SudokuField(4));
        list.add(new SudokuField(5));
        list.add(new SudokuField(6));
        list.add(new SudokuField(7));
        list.add(new SudokuField(8));
        list.add(new SudokuField(9));
        return list;
    }
    @Test
    public void verifyValidTest() {

        SudokuRow sudokuRow = new SudokuRow(createList());
        assertTrue(sudokuRow.verify());
    }

    @Test
    public void verifyInvalidTest() {
        List<SudokuField> list = createList();
        list.set(2, new SudokuField(6));
        SudokuRow sudokuRow = new SudokuRow(list);
        assertFalse(sudokuRow.verify());
    }

    @Test
    public void cloneTest() throws SudokuFieldObjectException {
        List<SudokuField> list = new ArrayList<>();
        list.add(new SudokuField(1));
        list.add(new SudokuField(2));
        list.add(new SudokuField(3));
        list.add(new SudokuField(4));
        list.add(new SudokuField(5));
        list.add(new SudokuField(6));
        list.add(new SudokuField(7));
        list.add(new SudokuField(8));
        list.add(new SudokuField(9));

        SudokuRow sudokuRow = new SudokuRow(list);
        SudokuRow sudokuRowDrugie = (SudokuRow) sudokuRow.clone();
        assertTrue(sudokuRow.equals(sudokuRowDrugie));
        assertTrue(sudokuRowDrugie.equals(sudokuRow));
    }
}
