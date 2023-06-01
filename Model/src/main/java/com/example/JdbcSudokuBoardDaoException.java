package com.example;

import java.io.IOException;
import java.sql.SQLException;

public class JdbcSudokuBoardDaoException extends SudokuException {
    public JdbcSudokuBoardDaoException(final String message, Throwable cause) {
        super(message, cause);
    }

    public JdbcSudokuBoardDaoException(final String message) {
        super(message);
    }

    public JdbcSudokuBoardDaoException(final Throwable throwable) {
        super(throwable);
    }

    public class JdbcSudokuBoardDaoIoException extends IOException {
        public JdbcSudokuBoardDaoIoException(final String message) {
            super(message);
        }
    }

    public static class JdbcSudokuBoardDaoSqlException extends SQLException {
        public JdbcSudokuBoardDaoSqlException(final String message) {
            super(message);
        }
    }

    public static class JdbcSudokuBoardDaoClassNotFoundException extends ClassNotFoundException {
        public JdbcSudokuBoardDaoClassNotFoundException(final String message) {
            super(message);
        }
    }

    public class JdbcSudokuBoardDaoRuntimeException extends RuntimeException {
        public JdbcSudokuBoardDaoRuntimeException(final String message) {
            super(message);
        }
    }
}
