package seedu.addressbook.commands;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

import java.util.HashSet;
import java.util.Set;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ":\n" + "Adds a person to the address book. "
            + "Contact details can be marked private by prepending 'p' to the prefix.\n\t"
            + "Parameters: NAME [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS [t/TAG]...\n\t"
            + "Parameters: NAME [p]p/PHONE1/PHONE2/... [p]e/EMAIL1/EMAIL2/... [p]a/ADDRESS1/ADDRESS2/...  [t/TAG]...\n\t"
            + "Examples: \n" + COMMAND_WORD
            + " John Doe p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney\n" + COMMAND_WORD
            + " Betty p/12345678/45294724 e/betty@gmail.com/betty@yahoo.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney";
    
    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person toAdd;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public AddCommand(String name,
                      String phones[], boolean isPhonePrivate,
                      String emails[], boolean isEmailPrivate,
                      String addresses[], boolean isAddressPrivate,
                      Set<String> tags) throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new Person(
                new Name(name),
                new Phone(phones, isPhonePrivate),
                new Email(emails, isEmailPrivate),
                new Address(addresses, isAddressPrivate),
                new UniqueTagList(tagSet)
        );
    }

    public AddCommand(Person toAdd) {
        this.toAdd = toAdd;
    }

    public ReadOnlyPerson getPerson() {
        return toAdd;
    }

    @Override
    public CommandResult execute() {
        try {
            addressBook.addPerson(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return new CommandResult(MESSAGE_DUPLICATE_PERSON);
        }
    }

}
