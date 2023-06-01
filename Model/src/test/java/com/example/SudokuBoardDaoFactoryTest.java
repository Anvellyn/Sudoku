package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;

public class SudokuBoardDaoFactoryTest {
    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();

    @Test
    public void getFileDaoTest() {
        assertNotNull(factory.getFileDao("halo"));
    }
}
