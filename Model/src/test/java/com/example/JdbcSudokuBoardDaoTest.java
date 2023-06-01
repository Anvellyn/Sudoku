package com.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JdbcSudokuBoardDaoTest {
   @Test
    public void jdbcSudokuBoardDaoReadException() {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        assertThrows(JdbcSudokuBoardDaoException.JdbcSudokuBoardDaoSqlException.class, ()->{factory.getDbDao("", true).read();});
    }

    @Test
    public void jdbcSudokuBoardDaoWriteException() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        assertThrows(JdbcSudokuBoardDaoException.JdbcSudokuBoardDaoSqlException.class, ()->{factory.getDbDao("graTrudna", true).write(sudokuBoard);});
    }

    @Test
    public void jdbcSudokuBoardDaoWriteException2() {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sudokuBoard.set(i, j, 1);
            }
        }
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        assertThrows(JdbcSudokuBoardDaoException.JdbcSudokuBoardDaoSqlException.class, ()->{factory.getDbDao("graTrudna", true).write(sudokuBoard);});
    }

    @Test
    public void factoryTest() throws JdbcSudokuBoardDaoException.JdbcSudokuBoardDaoSqlException, FileSudokuBoardDaoException, JdbcSudokuBoardDaoException, JdbcSudokuBoardDaoException.JdbcSudokuBoardDaoClassNotFoundException {
        SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
        Dao<SudokuBoard> dao = factory.getDbDao("testyyy", true);
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sb = new SudokuBoard(solver);
        solver.solve(sb);
        dao.write(sb);
        SudokuBoard newSB = dao.read();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assertEquals(sb.get(i, j), newSB.get(i, j));
            }
        }
       assertFalse(sb.equals(newSB));
    }
}
