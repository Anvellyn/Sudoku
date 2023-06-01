package pl.comp;

import com.example.Authors;
import com.example.PoziomTrudnosci;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class Controller {

    private final Logger logger = Logger.getLogger(Controller.class);

    private PoziomTrudnosci poziomTrudnosci;
    private Stage stage;
    Locale defLoc = new Locale("en");
    private Integer languageFlag = 0;
    Authors aut;

    public PoziomTrudnosci getPoziomTrudnosci() {
        return poziomTrudnosci;
    }

    ResourceBundle texts;


    @FXML
    private Label chooseLabel;

    @FXML
    private Label autorzyLabel;

    @FXML
    private Label autorzyLabel1;

    @FXML
    private Label autorzyLabel2;

    @FXML
    private Button engButton;

    @FXML
    private Button plButton;
    @FXML
    private Button easyButton;

    @FXML
    private Button hardButton;

    @FXML
    private Button mediumButton;

    public Controller() throws FxmlNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/WyborPoziomu.fxml"));
        fxmlLoader.setController(this);
        stage = new Stage();
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
        } catch (IOException e) {
            logger.error("Nie udalo sie utworzyc sudoku.");
            throw new FxmlNotFoundException(texts.getString("exceptionText"), e);
        }
    }


    @FXML
    public void initialize() {
        texts = ResourceBundle.getBundle("textMenu", defLoc);
        Authors aut = new Authors();
        autorzyLabel.setText(aut.getString("autorzy"));
        autorzyLabel1.setText(aut.getString("autor1"));
        autorzyLabel2.setText(aut.getString("autor2"));
        easyButton.setText(texts.getString("easy"));
        mediumButton.setText(texts.getString("medium"));
        hardButton.setText(texts.getString("hard"));
        autorzyLabel.setText(texts.getString("authors"));
        chooseLabel.setText(texts.getString("chooseLabel"));
        initButtons();
    }

    public void showStage() {
        stage.showAndWait();
    }

    public void initButtons() {
        easyButton.setOnAction(event -> chooseEasy());
        mediumButton.setOnAction(event -> chooseMedium());
        hardButton.setOnAction(event -> chooseHard());
    }

    void chooseEasy() {
        try {
            logger.info("Zaladowano gre na poziomie latwym");
            poziomTrudnosci = PoziomTrudnosci.Easy;
            BoardController boardController =
                    new BoardController(PoziomTrudnosci.Easy, languageFlag);
            boardController.showStage();
        } catch (FxmlNotFoundException e) {
            logger.error(texts.getString("authors"), e);
        }
    }

    void chooseHard() {
        try {
        logger.info("Zaladowano gre na poziomie trudnym");
        poziomTrudnosci = PoziomTrudnosci.Hard;
        BoardController boardController = new BoardController(PoziomTrudnosci.Hard, languageFlag);
        boardController.showStage();
        } catch (FxmlNotFoundException e) {
            logger.error(texts.getString("authors"), e);
        }
    }

    void chooseMedium() {
        try {
        logger.info("Zaladowano gre na poziomie srednim");
        poziomTrudnosci = PoziomTrudnosci.Medium;
        BoardController boardController = new BoardController(PoziomTrudnosci.Medium, languageFlag);
        boardController.showStage();
    } catch (FxmlNotFoundException e) {
        logger.error(texts.getString("authors"), e);
    }
    }

    @FXML
    void engButtonOn() {
        logger.info("Wybrano jezyk angielski");
        languageFlag = 0;
        defLoc = new Locale("en");
        initialize();
    }

    @FXML
    void plButtonOn() {
        logger.info("Wybrano jezyk polski");
        languageFlag = 1;
        defLoc = new Locale("pl");
        initialize();
    }


}
