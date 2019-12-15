package main.java.Model;

import main.java.Controller.BoardController;
import main.java.Model.GameObjects.BoardItem;
import main.java.Model.GameObjects.Box;
import main.java.Model.GameObjects.Dot;
import main.java.Model.GameObjects.Line;
import main.java.Model.Shapes.BoxShape;
import main.java.Model.Shapes.LineShape;
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
        computer();
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

    private void createLineAs(String type, Line line) {
        LineShape lineShape = new LineShape(line);
        line.setLineShape(lineShape);

        if (type == "horizontal") {
            lineShape.setHeight(15);
            lineShape.setWidth(75);
            lineShape.setLayoutX(positionX);
            positionX += 74;
        } else if (type == "vertical") {
            lineShape.setHeight(75);
            lineShape.setWidth(15);
            lineShape.setLayoutX(positionX);
            positionX += 14;
        }

        lineShape.setLayoutY(positionY);
        lineShape.setFill(Color.valueOf("#d0e1f2"));
        lineShape.setOnMouseEntered(e -> board.setOnLineEntered(lineShape));
        lineShape.setOnMouseExited(e -> board.setOnLineExited(lineShape));
        lineShape.setOnMouseClicked(e -> board.lineClicked(lineShape));
        lineShape.setStroke(Color.BLACK);
        lineShape.setStrokeType(StrokeType.INSIDE);
        board.addItemToBoard(lineShape);
    }

    private void computer(){
        board.computerMoveButton.setOnMouseClicked(e -> board.computerMove());
    }

    private float determinateWhatPositionYShouldBe(List<List<BoardItem>> listOfBoardItems, float positionY, List<BoardItem> rowOfBoardItems) {
        if ((listOfBoardItems.indexOf(rowOfBoardItems) & 1) == 0) {
            positionY += 14;
        } else {
            positionY += 74;
        }
        return positionY;
    }

    private void determineWhatTypeOfLineShouldBeMade(List<List<BoardItem>> listOfBoardItems, List<BoardItem> rowOfBoardItems, Line line) {
        if ((listOfBoardItems.indexOf(rowOfBoardItems) & 1) == 0) {
            createLineAs("horizontal", line);
        } else {
            createLineAs("vertical", line);
        }
    }
}
