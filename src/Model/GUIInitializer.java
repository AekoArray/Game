package Model;

import Controller.BoardController;
import Controller.SetupController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Created by Paul van der Bles on 9-8-2017.
 */
public class GUIInitializer {

    private GameEngine engine;
    private Stage primaryStage;
    private BoardController boardController;

    public void setRows(int input) {
        engine.setRows(input);
    }

    public void setColumns(int input) {
        engine.setColumns(input);
    }

    public boolean isRowsSet() {
        return engine.getRows() != 0;
    }

    public boolean isColumnsSet() {
        return engine.getColumns() != 0;
    }

    private void setTitle(Stage primaryStage) {
        primaryStage.setTitle("Dots and boxes");
    }

    void initializeGUI(Stage primaryStage, GameEngine engine) {
        this.engine = engine;
        setTitle(primaryStage);
        loadSetupScreen(primaryStage);
        this.primaryStage = primaryStage;
    }

    private void loadSetupScreen(Stage primaryStage) {
        FXMLLoader loader = instantiateFXMLLoader("Setup.fxml");
        loader.setController(new SetupController(this));
        setSceneOnStage(primaryStage, loader);
        primaryStage.show();
    }

    public void checkIfBoardIsReady() {
        if (isRowsSet() && isColumnsSet()) {
            buildBoard(primaryStage);
        }
    }

    private void buildBoard(Stage primaryStage) {
        FXMLLoader loader = instantiateFXMLLoader("Board.fxml");
        setSceneOnStage(primaryStage, loader);
        setReferences(loader);
        attachSceneToController(primaryStage);
        List listOfBoardItems = new ItemsBuilder().configureBoardItems(engine);
        new BoardBuilder().drawItemsOnBoard(listOfBoardItems, boardController);
        primaryStage.show();
    }

    private FXMLLoader instantiateFXMLLoader(String fileName) {
        return new FXMLLoader(getClass().getResource("/View/" + fileName));
    }

    private void setSceneOnStage(Stage primaryStage, FXMLLoader loader) {
        try {
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
        } catch (IOException e) {
            System.out.println("Something went wrong while setting scene on the primary stage");
        }
    }

    private void setReferences(FXMLLoader loader) {
        boardController = loader.getController();
        boardController.setEngine(engine);
        engine.setBoardController(boardController);
    }

    private void attachSceneToController(Stage primaryStage) {
        boardController.setPrimaryScene(primaryStage.getScene());
    }
}
