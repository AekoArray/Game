package main.java.Model;

import main.java.Client;
import main.java.Controller.BoardController;
import main.java.Model.GameObjects.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    private GUIInitializer guiInitializer;
    private Stage primaryStage;
    private Board board;
    private List<List<BoardItem>> boardItems;
    private Player playerOne;
    private Player computer;
    private Player currentPlayer;
    private BoardController boardController;

    public void setBoardItems(List<List<BoardItem>> boardItems) {
        this.boardItems = boardItems;
    }

    public void setBoardController(BoardController boardController) {
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

    public void synchronizeLinesWithServer(final List<List<Line>> linesFromServer) {
        final List<Line> transformedLines = new ArrayList<>();
        for (List<Line> lines : linesFromServer) {
            transformedLines.addAll(lines);
        }
        final List<Line> localLines = getAllLines();
        for (int i = 0; i < localLines.size(); i++) {
            localLines.get(i).setFillStatus(transformedLines.get(i).getFillStatus());//чтобы все линии считывались
        }
    }

    public List<Line> getAllLines() {
        final List<Line> lines = new ArrayList<>();
        for (List<BoardItem> boardItem : boardItems) {
            for (BoardItem item : boardItem) {
                if (item instanceof Line) {
                    lines.add((Line) item);
                }
            }
            }
            return lines;
    }

    public List<Line> getUnfilledLines() {
        final List<Line> lines = new ArrayList<>();
        for (List<BoardItem> boardItem : boardItems) {
            for (BoardItem item : boardItem) {
                if (item instanceof Line) {
                    final Line line = (Line) item;
                    if (!line.getFillStatus()) {
                        lines.add((Line) item);
                    }
                }
            }
        }
        return lines;
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
        } else {
            boardController.changeBoxColour(clickedLine.getAttachedBoxes(), currentPlayer);
            currentPlayer.addPoints(attachedBoxes * 10);
            boardController.changeScore(currentPlayer, playerOne, computer);
        }
        if (currentPlayer == computer) {
            boardController.computerMove();
        }
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
        } else if (playerOne.getScore() < computer.getScore()) {
            boardController.showWinner(computer);
        } else {
            boardController.showTie();
        }
    }
}
