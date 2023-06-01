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

public class Win {

    private final Logger logger = Logger.getLogger(Win.class);
    Locale defLoc = new Locale("pl");
    ResourceBundle texts;
    private int languageFlag = 0;
    private Stage stage;
    @FXML
    private Label winText;

    public int getLanguageFlag() {
        return languageFlag;
    }

    public Win(int languageFlag) throws FxmlNotFoundException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/win.fxml"));
        fxmlLoader.setController(this);
        this.languageFlag = languageFlag;
        if (languageFlag == 1) {
            defLoc = new Locale("pl");
        }
        if (languageFlag == 0) {
            defLoc = new Locale("en");
        }
        texts = ResourceBundle.getBundle("textWin", defLoc);

        stage = new Stage();
        try {
            stage.setScene(new Scene(fxmlLoader.load()));
            stage.sizeToScene();
            stage.show();
        } catch (IOException e) {
            throw new FxmlNotFoundException(texts.getString("exceptionText"), e);
        }

        logger.info("Sprawdzenie - wygrana.");
        winText.setText(texts.getString("winTextt"));
    }

    public void showStage() {
        stage.showAndWait();
    }
}
