package com.example;

import org.apache.commons.lang3.SystemUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class FileSudokuBoardDaoTest {
    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private BacktrackingSudokuSolver sudokuSolver= new BacktrackingSudokuSolver();
    private SudokuBoard sudokuBoard = new SudokuBoard(sudokuSolver);

    private Dao<SudokuBoard> fileSudokuBoardDao;
    private SudokuBoard sudokuBoardSecond;


    @Test
    public void readFIleSudokuBoardDaoException() {
        boolean thrown = false;
        fileSudokuBoardDao = factory.getFileDao("halooco");
        assertThrows(FileSudokuBoardDaoException.class, ()->{fileSudokuBoardDao.read();});
    }

    @Test
    public void writeFileSudokuBoardDaoException() {
        if (SystemUtils.IS_OS_WINDOWS) {
            fileSudokuBoardDao = factory.getFileDao("?");
        } else if (SystemUtils.IS_OS_LINUX) {
            fileSudokuBoardDao = factory.getFileDao("/");
        } else {
            fileSudokuBoardDao = factory.getFileDao("?");
        }
        assertThrows(FileSudokuBoardDaoException.class, ()->{fileSudokuBoardDao.write(sudokuBoard);});
    }









}
