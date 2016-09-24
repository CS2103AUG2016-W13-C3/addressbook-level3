package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Edits a person in the address book. "
            + "Contact details can be marked private by prepending 'p' to the prefix.\n\t"
            + "Parameters: INDEX [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS  [t/TAG]...\n\t"
            + "Example: " + COMMAND_WORD
            + " 1 p/98765432";
    
    public static final String MESSAGE_SUCCESS = "Person's details succesfully edited";
    public static final String MESSAGE_INVALID_NAME = "Proposed index is invalid.";

    private String editedInfo;
    
    public EditCommand(int targetIndex, String details) {
        super(targetIndex);
        this.editedInfo = details;
        
    }
    
    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            addressBook.editPerson(getTargetIndex(), editedInfo);
        } catch (PersonNotFoundException e) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        } catch (IllegalValueException e) {
            return new CommandResult(MESSAGE_INVALID_NAME);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

}