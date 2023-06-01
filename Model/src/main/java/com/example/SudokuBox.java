package com.example;


import java.util.ArrayList;
import java.util.List;

public class SudokuBox extends SudokuFieldObject {

    public SudokuBox(final List<SudokuField> array) {
        super(array);
    }

    @Override
    public Object clone() {
        List<SudokuField> fields = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            fields.add(this.get(i));
        }
        SudokuBox sudokuBox = new SudokuBox(fields);
        return sudokuBox;
    }

}
