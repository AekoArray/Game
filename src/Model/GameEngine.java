package Model;

import Controller.BoardController;
import Model.GameObjects.*;
import javafx.stage.Stage;

import java.util.List;

/**
 * Created by Paul van der Bles on 9-8-2017.
 */
public class GameEngine {

    private GUIInitializer guiInitializer;
    private Stage primaryStage;
    private Board board;
    private List<List<BoardItem>> boardItems;
    private Player playerOne;
    private Player computer;
    private Player currentPlayer;
    private BoardController boardController;

    void setBoardController(BoardController boardController) {
        this.boardController = boardController;
    }

    void setRows(int rows) {
        board.setRows(rows);
    }

    int getRows() {
        return board.getRows();
    }

    void setColumns(int columns) {
        board.setColumns(columns);
    }

    int getColumns() {
        return board.getColumns();
    }

    void setBoardItems(List boardItems) {
        this.boardItems = boardItems;
    }

    public String getCurrentPlayerColour() {
        return currentPlayer.getColourValue();
    }

    public GameEngine(GUIInitializer guiInitializer, Stage primaryStage) {
        this.guiInitializer = guiInitializer;
        this.primaryStage = primaryStage;
        this.board = new Board();
        initializePlayers();
        startSetup();
    }

    private void initializePlayers() {
        playerOne = new Player("#ff1f1f", "Player 1");
        computer = new Player("#1e90ff", "Computer");
        currentPlayer = playerOne;
    }

    private void startSetup() {
        guiInitializer.initializeGUI(primaryStage, this);
    }

    public void turn(Line clickedLine) {
        clickedLine.setFillStatus(true);
        int attachedBoxes = clickedLine.howManyAttachedBoxesAreFilledSinceThisTurn();

        if (checkIfAllBoxesAreFilled()) {
            determineWinner();
        }

        if (attachedBoxes <= 0) {
            currentPlayer = changePlayer();
            if(currentPlayer == computer){
                computerMove();
            }
        } else {
            boardController.changeBoxColour(clickedLine.getAttachedBoxes(), currentPlayer);
            currentPlayer.addPoints(attachedBoxes * 10);
            boardController.changeScore(currentPlayer, playerOne, computer);
        }

    }

    private void computerMove() {


    }

    private boolean checkIfAllBoxesAreFilled() {
        boolean allBoxesAreFilled = true;
        for (List<BoardItem> rowOfBoardItems : boardItems) {
            for (BoardItem item : rowOfBoardItems) {
                if (item instanceof Box) {
                    if (!((Box) item).shouldBeFilled()) {
                        allBoxesAreFilled = false;
                    }
                }
            }
        }
        return allBoxesAreFilled;
    }

    private Player changePlayer() {
        if (currentPlayer.equals(playerOne)) {
            return computer;
        } else {
            return playerOne;
        }
    }

    private void determineWinner() {
        if (playerOne.getScore() > computer.getScore()) {
            boardController.showWinner(playerOne);
        }
        else if (playerOne.getScore() < computer.getScore()) {
            boardController.showWinner(computer);
        }
        else {
            boardController.showTie();
        }
    }
}
