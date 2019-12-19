package main.java.Controller;

import main.java.Model.GUIInitializer;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;


/**
 * Created by Paul van der Bles on 9-8-2017.
 */
public class SetupController {

    private GUIInitializer initializer;

    @FXML
    Text message;

    @FXML
    TextField inputField;

    public SetupController(GUIInitializer initializer) {
        this.initializer = initializer;
    }

    public void prepareToSendBoardSizeAfterClick() { // gets input from setup window and checks if values are ok
        String input = getInput();
        if (checkBeforeSendingSize(input)) {
            int convertedInput = convertInputToInt(input);
            sendBoardSize(convertedInput);
        }
    }

    private boolean checkBeforeSendingSize(String input) {
        return inputIsInteger(input) && inputIsBetweenValues(convertInputToInt(input));
    }

    private boolean inputIsInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    private String getInput() {
        return inputField.getText();
    }

    private int convertInputToInt(String input) {
        return Integer.parseInt(input);
    }

    private boolean inputIsBetweenValues(int input) {
        return input >= 3 && input <= 9;
    }

    private void sendBoardSize(int convertedInput) {
        if (!initializer.isRowsSet()) {
            setRowSize(convertedInput);
        } else if (!initializer.isColumnsSet()) {
            SetColumnSize(convertedInput);
        }
        initializer.checkIfBoardIsReady();
    }

    private void setRowSize(int convertedInput) {
        initializer.setRows(convertedInput);
        changeColumnsMessage();
    }

    private void SetColumnSize(int convertedInput) {
        initializer.setColumns(convertedInput);
    }

    private void changeColumnsMessage() {
        message.setText("Enter a number of columns between 3 and 9");
    }
}