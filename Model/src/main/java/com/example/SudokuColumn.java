package com.example;


import java.util.ArrayList;
import java.util.List;

public class SudokuColumn extends SudokuFieldObject {

    public SudokuColumn(final List<SudokuField> array) {
        super(array);
    }

    @Override
    public Object clone() {
        List<SudokuField> fields = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            fields.add(this.get(i));
        }
        SudokuColumn sudokuColumn = new SudokuColumn(fields);
        return sudokuColumn;
    }
}
