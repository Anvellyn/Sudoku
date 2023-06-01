package com.example;

public interface Dao<T> extends AutoCloseable {
    T read() throws FileSudokuBoardDaoException,
            JdbcSudokuBoardDaoException.JdbcSudokuBoardDaoSqlException;

    void write(T obj) throws FileSudokuBoardDaoException,
            JdbcSudokuBoardDaoException.JdbcSudokuBoardDaoSqlException;
}
