package com.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SudokuColumnTest {
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

        SudokuColumn sudokuColumn = new SudokuColumn(createList());
        assertTrue(sudokuColumn.verify());
    }

    @Test
    public void verifyInvalidTest() {
        List<SudokuField> list = createList();
        list.set(2, new SudokuField(6));
        SudokuColumn sudokuColumn = new SudokuColumn(list);
        assertFalse(sudokuColumn.verify());
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

        SudokuColumn sudokuColumn = new SudokuColumn(list);
        assertEquals(sudokuColumn.get(8).getFieldValue(), 9);
        SudokuColumn sudokuColumnDrugie = (SudokuColumn) sudokuColumn.clone();
        assertTrue(sudokuColumn.equals(sudokuColumnDrugie));
        assertTrue(sudokuColumnDrugie.equals(sudokuColumn));
    }
}
