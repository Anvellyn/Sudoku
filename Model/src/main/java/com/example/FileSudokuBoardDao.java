package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {
    private String filename;



    public FileSudokuBoardDao(String filename) {
        this.filename = filename;
    }

    @Override
    public final SudokuBoard read() throws FileSudokuBoardDaoException {
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            Object obj = objectIn.readObject();
            return (SudokuBoard) obj;
        } catch (IOException e) {
            throw new FileSudokuBoardDaoException(null, e);
        } catch (ClassNotFoundException e) {
            throw new FileSudokuBoardDaoException(null, e);
        }
    }

    @Override
    public void write(SudokuBoard obj) throws FileSudokuBoardDaoException {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(obj);

        } catch (Exception ex) {
            throw new FileSudokuBoardDaoException(null, ex);
        }
    }

    @Override
    public void close() throws FileSudokuBoardDaoException{
    }
}
