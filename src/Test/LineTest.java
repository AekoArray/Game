package Test;

import Controller.BoardController;
import Model.GUIInitializer;
import Model.GameEngine;
import Model.GameObjects.Line;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.Assert;
import org.junit.Test;

public class LineTest {

    @Test
    public void fillStatusIsFill(){
        Line line = new Line();
        GUIInitializer guiInitializer = new GUIInitializer();
        Stage primaryStage = guiInitializer.getPrimaryStage();
        GameEngine ge = new GameEngine(guiInitializer, primaryStage);
        ge.turn(line);
        Assert.assertTrue(line.getFillStatus());
    }
}
