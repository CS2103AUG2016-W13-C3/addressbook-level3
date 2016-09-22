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
     * Adds `input` to input history and resets navigation index to (1 + index of latest input)
     */
    public void addToInputHistory(String input) {
        inputHistory.add(input);
        inputHistoryNavIndex = inputHistory.size();
    }

    /**
     * Moves the navigation index one step earlier
     * Index can be out of range of input history
     */
    public void moveBackInInputHistory() {
        inputHistoryNavIndex --;
    }

    /**
     * Moves the navigation index one step later
     * Index can be out of range of input history
     */
    public void moveForwardInInputHistory() {
        inputHistoryNavIndex ++;
    }

    /**
     * Returns input at navigation index in input history
     * If index is out of range of navigation index, empty string is returned
     */
    public String getCurInputInHistory() {
        if (inputHistoryNavIndex >= 0 && inputHistoryNavIndex < inputHistory.size()) {
            return inputHistory.get(inputHistoryNavIndex);
        } else {
            return "";
        }
    }

}
