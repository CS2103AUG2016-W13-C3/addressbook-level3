package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.*;

/**
 * Finds and lists all persons in address book whose phone number is same as the searching one.
 */
public class SearchCommand extends Command {

    public static final String COMMAND_WORD = "search";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Search the owner of a phone number "
            + "and displays the infomation of the owner, one number each input.\n\t"
            + "Parameters: PHONENUMBER\n\t"
            + "Example: " + COMMAND_WORD + " 90504011";

    private final String phoneNumber;

    public SearchCommand(String phone) {
        phoneNumber = phone;
    }

    /**
     * Returns copy of phone number in this command.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> personsFound = getPersonsWithSearchedPhone(phoneNumber);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieve all persons in the address book whose names contain the searching phone number.
     *
     * @param phone number for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithSearchedPhone(String phone) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            for (final String phoneNumber : person.getPhone().value) {
                if (phoneNumber.equals(phone)) {
                    matchedPersons.add(person);
                }
            }
        }
        return matchedPersons;
    }

}
