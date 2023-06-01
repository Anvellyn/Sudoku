package com.example;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class SudokuBoard implements Serializable, Cloneable {
    private final SudokuSolver sudokuSolver;
    private List<List<SudokuField>> board;
    Random rand = new Random();

    public SudokuBoard(SudokuSolver sudokuSolver) {
        board = Arrays.asList(new List[9]);
        this.sudokuSolver = sudokuSolver;
        for (int i = 0; i < 9; i++) {
            board.set(i, Arrays.asList(new SudokuField[9]));
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                this.board.get(i).set(j, new SudokuField());
            }
        }
    }

    public int get(int x, int y) {
        return board.get(x).get(y).getFieldValue();
    }

    public void set(int x, int y, int value) {
        this.board.get(x).get(y).setFieldValue(value);
    }

    public boolean set(int x, int y, int value, boolean verify) {
        if (verify) {
            this.board.get(x).get(y).setFieldValue(value);
            SudokuRow sudokuRow = this.getRow(x);
            SudokuColumn sudokuColumn = this.getColumn(y);
            SudokuBox sudokuBox = this.getBox(x, y);
            if (!sudokuRow.verify()) {
                this.set(x,y,0);
                return false;
            }
            if (!sudokuColumn.verify()) {
                this.set(x,y,0);
                return false;
            }
            if (!sudokuBox.verify()) {
                this.set(x,y,0);
                return false;
            }
            return true;
        } else {
            this.board.get(x).get(y).setFieldValue(value);
            return true;
        }
    }

    public void setEditableField(int axisX, int axisY) {
        board.get(axisX).get(axisY).setEmptyField();
    }

    public SudokuRow getRow(int row) {
        SudokuField[] rowField = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            rowField[i] = new SudokuField(this.board.get(row).get(i).getFieldValue());
        }
        return new SudokuRow(Arrays.asList(rowField));
    }

    public SudokuColumn getColumn(int column) {
        SudokuField[] columnField = new SudokuField[9];
        for (int i = 0; i < 9; i++) {
            columnField[i] = new SudokuField(this.board.get(i).get(column).getFieldValue());
        }
        return new SudokuColumn(Arrays.asList(columnField));
    }

    public SudokuBox getBox(int x, int y) {
        SudokuField[] boxField = new SudokuField[9];
        int index = 0;
        int boxRow = x - x % 3;
        int boxColumn = y - y % 3;
        for (int i = boxRow; i < boxRow + 3; i++) {
            for (int j = boxColumn; j < boxColumn + 3; j++) {
                boxField[index++] = new SudokuField(this.board.get(i).get(j).getFieldValue());
            }
        }
        return new SudokuBox(Arrays.asList(boxField));
    }

    public void solveGame() {
        sudokuSolver.solve(this);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o == this) {
            return true;
        }
        if (o.getClass() != getClass()) {
            return false;
        }

        SudokuBoard that = (SudokuBoard) o;
        return new EqualsBuilder()
                .append(sudokuSolver, that.sudokuSolver)
                .append(board, that.board)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(sudokuSolver)
                .append(board)
                .toHashCode();
    }

    @Override
    public Object clone() throws SudokuBoardException.SudokuBoardCloneNotSupportedException {
        SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard.set(i, j, get(i, j));
            }
        }
        return sudokuBoard;
    }

    public SudokuBoard fillSudokuWith0(int ileDoUsuniecia) {
        int x;
        int y;
        for (int i = 0; i < ileDoUsuniecia; i++) {
            do  {
                x = rand.nextInt(9);
                y = rand.nextInt(9);
            } while (this.get(x,y) == 0);
            this.set(x, y, 0);
        }
        return this;
    }

    public String changeEditListToString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                builder.append(String.valueOf(isEditableField(i, j) ? 1 : 0));
            }
        }

        return builder.toString();
    }

    public SudokuBoard changeStringToEditList(String string) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (string.charAt(i * 9 + j) == '1') {
                    setEditableField(i, j);
                }
            }
        }

        return this;
    }

    public String changeSudokuBoardToString() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                builder.append(String.valueOf(get(i, j)));
            }
        }

        return builder.toString();
    }

    public boolean isEditableField(int axisX, int axisY) {
        if (board.get(axisX).get(axisY).getFieldValue() == 0) {
            return true;
        }
        return false;
    }

    public SudokuBoard changeStringToSudokuBoard(String string) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                set(i, j, Character.getNumericValue(string.charAt(i * 9 + j)));
            }
        }
        return this;
    }
}