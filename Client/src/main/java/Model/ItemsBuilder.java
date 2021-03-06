package main.java.Model;

import main.java.Model.GameObjects.BoardItem;
import main.java.Model.GameObjects.Box;
import main.java.Model.GameObjects.Dot;
import main.java.Model.GameObjects.Line;

import java.util.ArrayList;
import java.util.List;

public class ItemsBuilder {

    public  List<List<BoardItem>> configureBoardItems(GameEngine engine) {
        final List<List<BoardItem>> listOfBoardItems = createListOfBoardItems(engine.getColumns(), engine.getRows());
        attachBoxesToLines(listOfBoardItems);
        engine.setBoardItems(listOfBoardItems);
        return listOfBoardItems;
    }

    public List<List<Line>> transformDataFromServerToList(final int[][] arr) {
        final List<List<Line>> lines = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            List<Line> lineList = new ArrayList<>();
            for (int j = 0; j < arr[i].length; j++) {
                Line line = new Line();
                if (arr[i][j] == 1) {
                    line.setFillStatus(true);
                }
                lineList.add(line);
            }
            lines.add(lineList);
        }
        return lines;
    }

    private List<List<BoardItem>> createListOfBoardItems(int columns, int rows) { // Makes a list that represents the dots, boxes and lines on the board
        List<List<BoardItem>> boardList = new ArrayList<>();
        for (int currentRow = 0; currentRow <= (rows * 2); currentRow++) {
            List<BoardItem> objectsRow = new ArrayList<>();
            for (int currentColumn = 0; currentColumn < columns; currentColumn++) {
                addItemsToBoardRow(columns, currentRow, objectsRow, currentColumn);
            }
            boardList.add(objectsRow);
        }
        return boardList;
    }

    private void addItemsToBoardRow(int columns, int currentRow, List<BoardItem> objectsRow, int currentColumn) {
        if ((currentRow & 1) == 0) {
            addDotsAndLines(columns, objectsRow, currentColumn);
        } else {
            addLinesAndBoxes(columns, objectsRow, currentColumn);
        }
    }

    private void addDotsAndLines(int columns, List<BoardItem> objectsRow, int currentColumn) {
        objectsRow.add(new Dot());
        objectsRow.add(new Line());
        if (currentColumn == columns - 1) {
            objectsRow.add(new Dot());
        }
    }

    private void addLinesAndBoxes(int columns, List<BoardItem> objectsRow, int currentColumn) {
        objectsRow.add(new Line());
        objectsRow.add(new Box());
        if (currentColumn == columns - 1) {
            objectsRow.add(new Line());
        }
    }

    private void attachBoxesToLines(List<List<BoardItem>> listOfBoardItems) { // Sets the right references for a box with it's lines
        for (List<BoardItem> rowOfBoardItems : listOfBoardItems) {
            for (BoardItem item : rowOfBoardItems) {
                if (item instanceof Box) {
                    int indexOfRow = listOfBoardItems.indexOf(rowOfBoardItems);
                    int indexOfBox = rowOfBoardItems.indexOf(item);

                    Line northernLine = findAssociatedNorthernLine(listOfBoardItems, indexOfRow, indexOfBox);
                    Line easternLine = findAssociatedEasternLine(rowOfBoardItems, indexOfBox);
                    Line southernLine = findAssociatedSouthernLine(listOfBoardItems, indexOfRow, indexOfBox);
                    Line westernLine = findAssociatedWesternLine(rowOfBoardItems, indexOfBox);

                    ((Box) item).setLinesRelationship(northernLine, easternLine, southernLine, westernLine);
                }
            }
        }
    }

    private Line findAssociatedNorthernLine(List<List<BoardItem>> listOfBoardItems, int indexOfRow, int indexOfBox) {
        int targetRowIndex = indexOfRow - 1;
        List<BoardItem> rowThatContainsLine = listOfBoardItems.get(targetRowIndex);
        Line northernLine = (Line) rowThatContainsLine.get(indexOfBox); // the line should be on the same index as the box, but in a higher row
        return northernLine;
    }

    private Line findAssociatedEasternLine(List<BoardItem> rowThatContainsLine, int indexOfBox) {
        int targetLineIndex = indexOfBox + 1;
        Line easternLine = (Line) rowThatContainsLine.get(targetLineIndex);
        return easternLine;
    }

    private Line findAssociatedSouthernLine(List<List<BoardItem>> listOfBoardItems, int indexOfRow, int indexOfBox) {
        int targetRowIndex = indexOfRow + 1;
        List<BoardItem> rowThatContainsLine = listOfBoardItems.get(targetRowIndex);
        Line southernLine = (Line) rowThatContainsLine.get(indexOfBox); // the line should be on the same index as the box, but in a higher row
        return southernLine;
    }

    private Line findAssociatedWesternLine(List<BoardItem> rowThatContainsLine, int indexOfBox) {
        int targetLineIndex = indexOfBox - 1;
        Line westernLine = (Line) rowThatContainsLine.get(targetLineIndex);
        return westernLine;
    }
}
