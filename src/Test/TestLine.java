package Test;

import Model.GUIInitializer;
import Model.GameEngine;
import Model.GameObjects.Line;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;

public class TestLine {

    @Test
    public void fillStatusIsFill(){
        Line line = new Line();
        Stage primaryStage = new Stage();
        GameEngine ge = new GameEngine(new GUIInitializer(), primaryStage);
        ge.turn(line);
        Assert.assertTrue(line.getFillStatus());
    }
}
