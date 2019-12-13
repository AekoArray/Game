package Controller;

import Model.GameEngine;
import Model.GameObjects.BoardItem;
import Model.GameObjects.Box;
import Model.GameObjects.Line;
import Model.GameObjects.Player;
import Model.Shapes.LineShape;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class BoardController {

    @FXML
    private Scene primaryScene;

    @FXML
    private Pane boardPane;

    @FXML
    private Text playerOnePoints;

    @FXML
    private Text playerTwoPoints;

    @FXML
    private Pane menuPane;

    @FXML
    private Text winMessage;

    @FXML
    public Text computerMoveButton;

    private GameEngine engine;

    public void setEngine(GameEngine engine) {
        this.engine = engine;
    }

    public void setPrimaryScene(Scene primaryScene) {
        this.primaryScene = primaryScene;
    }

    public void setPanePosition(float positionY) {
        menuPane.setLayoutY(positionY + 10); // Make sure that the pain is located under the board items
    }

    public BoardController(){

    }

    public void computerMove(){
                System.out.println("ssss");
        for (List<BoardItem> boardItem : engine.getBoardItems()) {
            for (BoardItem item : boardItem) {
                if (item instanceof Line){
                    Line line = (Line) item;
                    if (!line.getFillStatus()){
                        lineClicked(line.getLineShape());
//                        int attachedBoxes = line.howManyAttachedBoxesAreFilledSinceThisTurn();
//
//                        if (engine.checkIfAllBoxesAreFilled()) {
//                            engine.determineWinner();
//                        }
//
//                        if (attachedBoxes <= 0) {
//                            engine.currentPlayer = engine.changePlayer();
//                        } else {
//                            changeBoxColour(line.getAttachedBoxes(), engine.getCurrentPlayer());
//                            engine.getCurrentPlayer().addPoints(attachedBoxes * 10);
//                           changeScore(engine.getCurrentPlayer(), engine.getPlayerOne(), engine.getComputer());
//                        }
                        return;
                    }
                }
            }
        }
    }

    public void lineClicked(LineShape clickedLineShape) { // When the line shape is clicked, this method will change the colour, cursor type and send the line to the turn method
        if (!clickedLineShape.getLineObject().getFillStatus()) {
            clickedLineShape.setFill(Color.BLACK);
            primaryScene.setCursor(Cursor.DEFAULT);
            Line lineObject = clickedLineShape.getLineObject();
            engine.turn(lineObject);
        }
    }

    public void setOnLineEntered(LineShape line) {
        if (!line.getLineObject().getFillStatus()) {
            line.setFill(Color.valueOf(engine.getCurrentPlayerColour()));
            primaryScene.setCursor(Cursor.HAND);
        }
    }

    public void setOnLineExited(LineShape line) {
        if (!line.getLineObject().getFillStatus()) {
            line.setFill(Color.valueOf("#d0e1f2"));
            primaryScene.setCursor(Cursor.DEFAULT);
        }
    }

    public void addItemToBoard(Rectangle item) {
        boardPane.getChildren().add(item);
    }

    public void changeScore(Player currentPlayer, Player playerOne, Player playerTwo) {
        String newScoreText = currentPlayer.getScore() + " Points";
        if (currentPlayer.equals(playerOne)) {
            playerOnePoints.setText(newScoreText);
        }
        if (currentPlayer.equals(playerTwo)) {
            playerTwoPoints.setText(newScoreText);
        }
    }

    public void changeBoxColour(ArrayList<Box> attachedBoxes, Player currentPlayer) {
        for (Box box : attachedBoxes) {
            if (box.fillStatus) {
                Paint usersColour = Paint.valueOf(currentPlayer.getColourValue());
                box.getBoxShape().setFill(usersColour);
            }
        }
    }

    public void showWinner(Player currentPlayer) {
        winMessage.setText(currentPlayer.getName() + " wins!");
    }

    public void showTie() {
        winMessage.setText("It's a tie!");
    }
}
