package Model;

import Controller.BoardController;
import Model.GameObjects.BoardItem;
import Model.GameObjects.Box;
import Model.GameObjects.Dot;
import Model.GameObjects.Line;
import Model.Shapes.BoxShape;
import Model.Shapes.LineShape;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.util.List;

public class BoardBuilder {
    private float positionX = 5;
    private float positionY = 5;
    private BoardController board;

    void drawItemsOnBoard(List<List<BoardItem>> listOfBoardItems, BoardController boardController) {
        board = boardController;

        for (List<BoardItem> rowOfBoardItems : listOfBoardItems) {
            for (BoardItem item : rowOfBoardItems) {
                if (item instanceof Dot) {
                    createDot();
                }
                if (item instanceof Box) {
                    createBox((Box)item);
                }
                if (item instanceof Line) {
                    determineWhatTypeOfLineShouldBeMade(listOfBoardItems, rowOfBoardItems, (Line)item);
                }
            }
            positionX = 5;
            positionY = determinateWhatPositionYShouldBe(listOfBoardItems, positionY, rowOfBoardItems);
        }
        boardController.setPanePosition(positionY);
    }

    private void createBox(Box boxObject) {
        BoxShape box = new BoxShape(boxObject);
        box.setHeight(75);
        box.setWidth(75);
        box.setLayoutX(positionX);
        box.setLayoutY(positionY);
        box.setFill(Color.WHITE);
        box.setStroke(Color.BLACK);
        box.setStrokeType(StrokeType.INSIDE);
        board.addItemToBoard(box);
        positionX += 74;
    }

    private void createDot() {
        Rectangle dot = new Rectangle();
        dot.setHeight(15);
        dot.setWidth(15);
        dot.setLayoutX(positionX);
        dot.setLayoutY(positionY);
        dot.setFill(Color.valueOf("#363b40"));
        dot.setStroke(Color.BLACK);
        dot.setStrokeType(StrokeType.INSIDE);
        board.addItemToBoard(dot);
        positionX += 14;
    }

    private void createLineAs(String type, Line lineObject) {
        LineShape line = new LineShape(lineObject);

        if (type == "horizontal") {
            line.setHeight(15);
            line.setWidth(75);
            line.setLayoutX(positionX);
            positionX += 74;
        } else if (type == "vertical") {
            line.setHeight(75);
            line.setWidth(15);
            line.setLayoutX(positionX);
            positionX += 14;
        }

        line.setLayoutY(positionY);
        line.setFill(Color.valueOf("#d0e1f2"));
        line.setOnMouseEntered(e -> board.setOnLineEntered(line));
        line.setOnMouseExited(e -> board.setOnLineExited(line));
        line.setOnMouseClicked(e -> board.lineClicked(line));
        line.setStroke(Color.BLACK);
        line.setStrokeType(StrokeType.INSIDE);
        board.addItemToBoard(line);
    }

    private float determinateWhatPositionYShouldBe(List<List<BoardItem>> listOfBoardItems, float positionY, List<BoardItem> rowOfBoardItems) {
        if ((listOfBoardItems.indexOf(rowOfBoardItems) / 2) == 0) {
            positionY += 14;
        } else {
            positionY += 74;
        }
        return positionY;
    }

    private void determineWhatTypeOfLineShouldBeMade(List<List<BoardItem>> listOfBoardItems, List<BoardItem> rowOfBoardItems, Line line) {
        if ((listOfBoardItems.indexOf(rowOfBoardItems) / 2) == 0) {
            createLineAs("horizontal", line);
        } else {
            createLineAs("vertical", line);
        }
    }
}
