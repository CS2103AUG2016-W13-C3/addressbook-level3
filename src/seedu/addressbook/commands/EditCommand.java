package seedu.addressbook.commands;

import java.util.HashSet;
import java.util.Set;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Edits a person in the address book. "
            + "Contact details can be marked private by prepending 'p' to the prefix.\n\t"
            + "Parameters: INDEX [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS  [t/TAG]...\n\t"
            + "Example: " + COMMAND_WORD
            + " 1 p/98765432";
    
    public static final String MESSAGE_SUCCESS = "Person's details succesfully edited";
    public static final String MESSAGE_INVALID_NAME = "Proposed index is invalid.";

    private String[] editPhones;
    private String[] editEmails;
    private String[] editAddresses;
    private boolean editPhonePrivacy;
    private boolean editEmailPrivacy;
    private boolean editAddressPrivacy;
    private UniqueTagList taglist;
    
    /**
     * Convenience constructor using raw values.
     * @param targetIndex 
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public EditCommand(int targetIndex, String personIndex,
                      String[] phones, boolean isPhonePrivate,
                      String[] emails, boolean isEmailPrivate,
                      String[] addresses, boolean isAddressPrivate,
                      Set<String> tags) throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        
        this.editPhones = phones;
        this.editEmails = emails;
        this.editAddresses = addresses;
        this.editPhonePrivacy = isPhonePrivate;
        this.editEmailPrivacy = isEmailPrivate;
        this.editAddressPrivacy = isAddressPrivate;
        this.taglist = new UniqueTagList(tagSet);
    }
    
    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            addressBook.editPerson(getTargetIndex(), 
                    editPhones, editPhonePrivacy, 
                    editEmails, editEmailPrivacy, 
                    editAddresses, editAddressPrivacy,
                    taglist);
        } catch (PersonNotFoundException e) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        } catch (IllegalValueException e) {
            return new CommandResult(MESSAGE_INVALID_NAME);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }

}