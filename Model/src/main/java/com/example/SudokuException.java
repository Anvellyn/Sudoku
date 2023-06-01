package com.example;

public class SudokuException extends Exception {
    public SudokuException(final String message, Throwable cause) {
        super(message, cause);
    }

    public SudokuException(final String message) {
        super(message);
    }

    public SudokuException(final Throwable throwable) {
        super(throwable);
    }

}
