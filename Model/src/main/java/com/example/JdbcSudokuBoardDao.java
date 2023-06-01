package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private final String host = "jdbc:mysql://localhost:3306/dbname";
    private final String Name = "root";
    private final String Pass = "admin";

    private Connection connection;
    ResultSet rs;
   Statement statement;
   private String name;

    public JdbcSudokuBoardDao(String name)
            throws JdbcSudokuBoardDaoException.JdbcSudokuBoardDaoClassNotFoundException {
        this.name = name;
       try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(host, Name, Pass);
            statement = connection.createStatement();
        } catch (SQLException err) {
            throw new JdbcSudokuBoardDaoException.JdbcSudokuBoardDaoClassNotFoundException(null);
        } catch (ClassNotFoundException e) {
            throw new JdbcSudokuBoardDaoException.JdbcSudokuBoardDaoClassNotFoundException(null);
        }
    }

    @Override
    public void write(SudokuBoard sudokuBoard)
            throws JdbcSudokuBoardDaoException.JdbcSudokuBoardDaoSqlException {

        try (PreparedStatement preparedStatement = connection
                .prepareStatement(SQL_OBJECT);) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, sudokuBoard.changeSudokuBoardToString());
            preparedStatement.setString(3, sudokuBoard.changeEditListToString());
            preparedStatement.executeUpdate();
        } catch (SQLException err) {
            throw new JdbcSudokuBoardDaoException.JdbcSudokuBoardDaoSqlException(null);
        }
    }

    public void autocommit() throws JdbcSudokuBoardDaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new JdbcSudokuBoardDaoException(null, e);
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        connection.close();
        rs.close();
    }

    private static final String SQL_OBJECT
            = "INSERT INTO sudokuboards(SudokuBoardsName, fields, isEditable) VALUES (?, ?, ?)";

    @Override
    public void close() throws Exception {
    }

    @Override
    public SudokuBoard read() throws JdbcSudokuBoardDaoException.JdbcSudokuBoardDaoSqlException {
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        String receivedData;
        ResultSet resultSet;

        String selectData = "select SudokuBoardsName, fields, isEditable from "
                + "sudokuboards" + " where SudokuBoardsName=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectData)) {
                preparedStatement.setString(1, name);
                resultSet = preparedStatement.executeQuery();
                resultSet.next();
                    receivedData = resultSet.getString(2);
                    sudokuBoard.changeStringToSudokuBoard(receivedData);
                    sudokuBoard.changeStringToEditList(receivedData);

        } catch (SQLException e) {
            throw new JdbcSudokuBoardDaoException.JdbcSudokuBoardDaoSqlException(null);
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new JdbcSudokuBoardDaoException.JdbcSudokuBoardDaoSqlException(null);
        }

        return sudokuBoard;

    }
}
