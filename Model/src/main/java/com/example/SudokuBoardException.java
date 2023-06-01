package com.example;

public class SudokuBoardException extends SudokuException {

    public SudokuBoardException(final String message, Throwable cause) {
        super(message, cause);
    }

    public SudokuBoardException(final String message) {
        super(message);
    }

    public SudokuBoardException(final Throwable throwable) {
        super(throwable);
    }

    public class SudokuBoardCloneNotSupportedException extends CloneNotSupportedException {
        public SudokuBoardCloneNotSupportedException(final String message) {
            super(message);
        }
    }
}
