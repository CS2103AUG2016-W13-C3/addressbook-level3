package seedu.addressbook.ui;

import java.util.ArrayList;

/**
 * Helps the user with input
 */
public class InputHelper {

    private ArrayList<String> inputHistory = new ArrayList<>();
    private int inputHistoryNavIndex = -1;

    public InputHelper(){
    }

    /**
     * Adds `input` to input history and resets navigation index to latest input
     */
    public void addToInputHistory(String input) {
        inputHistory.add(input);
        inputHistoryNavIndex = inputHistory.size() - 1;
    }

    /**
     * Moves the navigation index one step earlier, if possible
     */
    public void moveBackInputHistory() {
        if (inputHistoryNavIndex > 0) {
            inputHistoryNavIndex --;
        }
    }

    /**
     * Moves the navigation index one step later, if possible
     */
    public void moveForwardInputHistory() {
        if (inputHistoryNavIndex < inputHistory.size() - 1) {
            inputHistoryNavIndex ++;
        }
    }

    /**
     * Returns input at navigation index in input history
     */
    public String getCurInputHistory() {
        if (inputHistoryNavIndex >= 0) {
            return inputHistory.get(inputHistoryNavIndex);
        } else {
            return "";
        }
    }

}
