package com.example;

public class SudokuBoardDaoFactory {
    public Dao<SudokuBoard> getFileDao(String filename) {
        return new FileSudokuBoardDao(filename);
    }

    public Dao getDbDao(String name, boolean autocommit) throws JdbcSudokuBoardDaoException,
            JdbcSudokuBoardDaoException.JdbcSudokuBoardDaoClassNotFoundException {
        JdbcSudokuBoardDao dao = new JdbcSudokuBoardDao(name);
        if (autocommit == true) {
            dao.autocommit();
        }
        return dao;
    }

}
