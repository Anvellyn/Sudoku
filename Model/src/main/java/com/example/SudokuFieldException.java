package com.example;

public class SudokuFieldException extends SudokuBoardException {
    public SudokuFieldException(final String message, Throwable cause) {
        super(message, cause);
    }

    public static class SudokuFieldNullPointerException extends NullPointerException {
        public SudokuFieldNullPointerException(final String message) {
            super(message);
        }
    }

    public static class SudokuFieldCloneNotSupportedException extends CloneNotSupportedException {
        public SudokuFieldCloneNotSupportedException(final String message) {
            super(message);
        }
    }
}
