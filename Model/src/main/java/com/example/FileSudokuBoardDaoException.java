package com.example;

import java.io.IOException;

public class FileSudokuBoardDaoException extends SudokuBoardException {

    public FileSudokuBoardDaoException(final String message, Throwable cause) {
        super(message, cause);
    }

    public FileSudokuBoardDaoException(final String message) {
        super(message);
    }

    public FileSudokuBoardDaoException(final Throwable throwable) {
        super(throwable);
    }

    public class FileSudokuBoardDaoIoException extends IOException {

        public FileSudokuBoardDaoIoException(final String message, Throwable cause) {
            super(message, cause);
        }

        public FileSudokuBoardDaoIoException(final String string) {
            super(string);
        }

        public FileSudokuBoardDaoIoException(final Throwable throwable) {
            super(throwable);
        }
    }

    public class FileSudokuBoardClassNotFoundException extends ClassNotFoundException {
        public FileSudokuBoardClassNotFoundException(final String string) {
            super(string);
        }
    }
}
