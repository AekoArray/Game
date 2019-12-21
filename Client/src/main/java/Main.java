package main.java;

import main.java.Model.GUIInitializer;
import main.java.Model.GameEngine;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GameEngine engine = new GameEngine(new GUIInitializer(), primaryStage);
        Client client = new Client();
        client.startClient(engine);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
