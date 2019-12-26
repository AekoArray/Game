package test.java;

import main.java.Controller.BoardController;
import main.java.Model.GUIInitializer;
import main.java.Model.GameEngine;
import main.java.Model.GameObjects.BoardItem;
import main.java.Model.GameObjects.Line;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

public class GameEngineTest {

    @Test
    public void fillStatusIsFill(){
        Line line = new Line();
        GUIInitializer mockGuiInitializer = Mockito.mock(GUIInitializer.class);
        List<List<BoardItem>> boardItems = Collections.emptyList();
        BoardController mockBoardController = Mockito.mock(BoardController.class);
        Stage mockStage = Mockito.mock(Stage.class);
        GameEngine ge = new GameEngine(mockGuiInitializer, mockStage);
        ge.setBoardController(mockBoardController);
        ge.setBoardItems(boardItems);
        ge.turn(line);
        Assert.assertTrue(line.getFillStatus());
    }
}
