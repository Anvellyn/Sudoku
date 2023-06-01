package pl.comp;

import com.example.*;
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;


public class BoardController {

    private boolean loadFromFile = false;
    private Vector<TextField> fields = new Vector<>();
    private final Logger logger = Logger.getLogger(BoardController.class);
    Locale defLoc;
    ResourceBundle texts;
    private int languageFlag = 0;
    String stringLoadFromDataBase;

    private FileSudokuBoardDao fileSudokuBoardDao;
    private FileChooser fileChooser = new FileChooser();
    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private Dao<SudokuBoard> jdbcSudokuBoardDao;
    private File file;

    SudokuBoard sudokuBoard;
    @FXML
    private Button loadDatabase;

    @FXML
    private Button saveDatabaseButton;
    @FXML
    private Button saveGameButton;
    @FXML
    private Button loadGameButton;
    @FXML
    private GridPane board;
    private Stage stage;
    private SudokuBoard sudokuBoardCopy = new SudokuBoard(new BacktrackingSudokuSolver());

    boolean isValid = true;
    private PoziomTrudnosci poziomTrudnosci;

    private SudokuBoard sudokuBoardDatabase = null;
    @FXML
    private Button checkBoardButton;

    public Stage getStage() {
        return stage;
    }

    public BoardController(PoziomTrudnosci poziomTrudnosci1, int languageFlag)
            throws FxmlNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Siatka.fxml"));
        fxmlLoader.setController(this);
        stage = new Stage();
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            logger.error("Nie udalo sie utworzyc sudoku.");
            throw new FxmlNotFoundException(texts.getString("exceptionText"), e);
        }
        poziomTrudnosci = poziomTrudnosci1;
        this.languageFlag = languageFlag;
        initBoard();
    }

    public void showStage() {
        stage.showAndWait();
    }


    private int jakiPoziomTrudnosci() {
        if (poziomTrudnosci == PoziomTrudnosci.Easy) {
            return 10;
        }
        if (poziomTrudnosci == PoziomTrudnosci.Medium) {
            return 30;
        }
        if (poziomTrudnosci == PoziomTrudnosci.Hard) {
            return 40;
        }
        return 0;
    }

    private void initBoard() {
        if (languageFlag == 0) {
            defLoc = new Locale("en");
        } else {
            defLoc = new Locale("pl");
        }
        texts = ResourceBundle.getBundle("textBoard", defLoc);
        checkBoardButton.setText(texts.getString("checkBoardButton"));
        saveGameButton.setText(texts.getString("saveBoardButton"));
        loadGameButton.setText(texts.getString("loadBoardButton"));
        saveDatabaseButton.setText(texts.getString("saveDataBaseButton"));
        loadDatabase.setText(texts.getString("loadDataBaseButton"));
        sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        logger.info("Stworzono sudoku");
        try {
            sudokuBoardCopy = (SudokuBoard) sudokuBoard.clone();
        } catch (SudokuBoardException.SudokuBoardCloneNotSupportedException e) {
            logger.error("Klonowanie nie powiodlo sie.");
        }
        SudokuBoard sudokuBoardDoZmiany = sudokuBoard.fillSudokuWith0(jakiPoziomTrudnosci());
        sudokuBoardBuild(sudokuBoardDoZmiany);
    }


    private void sudokuBoardBuild(SudokuBoard sudokuBoardDoZmiany) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                TextField textField = new TextField();
                textField.setMaxWidth(30);
                textField.setMaxHeight(10);
                if (sudokuBoardDoZmiany.get(i, j) == 0) {
                   textField.setText(Integer.toString(sudokuBoardDoZmiany.get(i, j)));
                    textField.textProperty().addListener((observable, oldValue, newValue) -> {
                        logger.info("Zmiana pola z " + oldValue + " na " + newValue);
                        binding();
                    });
                }
                if (sudokuBoardDoZmiany.get(i, j) != 0) {
                    textField.setEditable(false);
                    textField.setText(Integer.toString(sudokuBoardDoZmiany.get(i, j)));
                }

                board.add(textField, i, j);
            }
        }
    }

    private boolean isInputValid() {
        boolean isValid = true;
        for (int i = 0; i < 9 * 9; i++) {
            String fieldValue = ((TextField) board.getChildren().get(i)).getText();
            if (!((fieldValue.matches("[0-9]")) || (fieldValue.equals("")))) {
                isValid = false;
            }
        }

        return isValid;
    }

    private void binding() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String fieldValue = ((TextField) board
                        .getChildren().get(i * 9 + j)).getText();
                if (StringUtils.isNumeric(fieldValue)) {
                    sudokuBoard.set(i, j, Integer.parseInt(fieldValue));
                }
            }
        }
    }

    private boolean isSudokuSolved() {
        boolean isWon = true;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String fieldValue = ((TextField) board
                        .getChildren().get(i * 9 + j)).getText();
                if (!String.valueOf(sudokuBoardCopy.get(i, j)).equals(fieldValue)) {
                    isWon = false;
                }
            }
        }
        return isWon;
    }

    @FXML
    void checkBoardButtonOn() throws FxmlNotFoundException {
        logger.info("Wybrano sprawdzenie tablicy");
        if (isSudokuSolved() && isInputValid()) {
            Win win = new Win(languageFlag);
        } else {
            Lose lose = new Lose(languageFlag);
        }
    }


    private void updateBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String fieldValue = ((TextField) board
                        .getChildren().get(i * 9 + j)).getText();
                if (!fieldValue.equals("")) {
                    sudokuBoard.set(i, j, Integer.parseInt(fieldValue));
                } else {
                    sudokuBoard.set(i, j, 0);
                }
            }
        }
    }

    @FXML
    void saveGameButtonOn() throws FileSudokuBoardDaoException {
       logger.info("Zapis do pliku");
        File file = fileChooser.showSaveDialog(stage);
        FileSudokuBoardDao dao = new FileSudokuBoardDao(file.getAbsolutePath());
            dao.write(sudokuBoard);
    }

    @FXML
    void loadGameButtonOn() throws FileSudokuBoardDaoException {
       logger.info("Wczytanie z pliku");
        loadFromFile = true;
        File file = fileChooser.showOpenDialog(stage);
        FileSudokuBoardDao dao = new FileSudokuBoardDao(file.getAbsolutePath());
        SudokuBoard sudokuBoardLoaded = new SudokuBoard(new BacktrackingSudokuSolver());
            dao.read();
        initBoard();
    }


    @FXML
    void loadDataBase() throws JdbcSudokuBoardDaoException.JdbcSudokuBoardDaoSqlException,
            JdbcSudokuBoardDaoException.JdbcSudokuBoardDaoClassNotFoundException {
        TextInputDialog td = new TextInputDialog("Wpisz tekst");
        td.setHeaderText("Podaj nazwe sudoku jakie chcesz zaladowac");
        td.showAndWait();
        stringLoadFromDataBase = td.getEditor().getText();
        JdbcSudokuBoardDao dbDao = new JdbcSudokuBoardDao(stringLoadFromDataBase);
        sudokuBoardDatabase = dbDao.read();
        sudokuBoardBuild(sudokuBoardDatabase);
        sudokuBoard = sudokuBoardDatabase;
    }

    @FXML
    void saveDatabase() throws JdbcSudokuBoardDaoException.JdbcSudokuBoardDaoSqlException,
            JdbcSudokuBoardDaoException.JdbcSudokuBoardDaoClassNotFoundException {


        TextInputDialog td = new TextInputDialog("Wpisz tekst");
        td.setHeaderText("Podaj nazwe sudoku jakie chcesz zapisac");
        td.showAndWait();


        String stringSaveToDataBase = td.getEditor().getText();
        JdbcSudokuBoardDao dbDao = new JdbcSudokuBoardDao(stringSaveToDataBase);
        dbDao.write(sudokuBoard);
    }

}

