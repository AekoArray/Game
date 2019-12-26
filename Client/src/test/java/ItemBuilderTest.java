package test.java;

import javafx.stage.Stage;
import main.java.Controller.BoardController;
import main.java.Model.GUIInitializer;
import main.java.Model.GameEngine;
import main.java.Model.GameObjects.BoardItem;
import main.java.Model.GameObjects.Line;
import main.java.Model.ItemsBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

public class ItemBuilderTest {
    @Test
    public void UnfilledLinesIsNull(){

        GUIInitializer mockGuiInitializer = Mockito.mock(GUIInitializer.class);
        Stage mockStage = Mockito.mock(Stage.class);
        GameEngine ge = new GameEngine(mockGuiInitializer, mockStage);
        ItemsBuilder mockItemBuilder = Mockito.mock(ItemsBuilder.class);
        List<List<BoardItem>> boardItems = Collections.emptyList();
        Assert.assertEquals(mockItemBuilder.configureBoardItems(ge), boardItems);
    }
}
