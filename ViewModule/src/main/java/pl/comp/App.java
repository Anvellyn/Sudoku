package pl.comp;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.log4j.Logger;


public class App extends Application {
    private final Logger logger = Logger.getLogger(BoardController.class);

    @Override
    public void start(Stage stage) {
        try {
            Controller controller = new Controller();
            controller.showStage();
        } catch (FxmlNotFoundException e) {
            logger.error("Plik fxml nie zostal znaleziony", e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}
