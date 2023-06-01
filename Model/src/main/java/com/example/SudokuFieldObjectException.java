package com.example;

public class SudokuFieldObjectException extends SudokuException {
    public SudokuFieldObjectException(final String message, Throwable cause) {
        super(message, cause);
    }

    public static class BadListException extends IllegalArgumentException {
        public BadListException(final String message) {
            super(message);
        }
    }
}
