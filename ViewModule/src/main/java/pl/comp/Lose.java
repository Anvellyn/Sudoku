package pl.comp;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.apache.log4j.Logger;

public class Lose {
    private Stage stage;
    Locale defLoc = new Locale("pl");

    private final Logger logger = Logger.getLogger(Lose.class);
    ResourceBundle texts;
    private int languageFlag = 0;

    @FXML
    private Label loseText;

    public int getLanguageFlag() {
        return languageFlag;
    }

    public Lose(int languageFlag) throws FxmlNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/lose.fxml"));
        fxmlLoader.setController(this);
        stage = new Stage();
        this.languageFlag = languageFlag;
        if (languageFlag == 1) {
            defLoc = new Locale("pl");
        }
        if (languageFlag == 0) {
            defLoc = new Locale("en");
        }
        texts = ResourceBundle.getBundle("textLose", defLoc);
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.sizeToScene();
            stage.show();
        } catch (IOException e) {
            throw new FxmlNotFoundException(texts.getString("exceptionText"), e);
        }


        logger.info("Sprawdzenie - przegrana.");
        loseText.setText(texts.getString("loseText"));
    }

    public void showStage() {
        stage.showAndWait();
    }
}
