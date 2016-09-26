package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.tag.UniqueTagList;

import java.util.Objects;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */
public class Person implements ReadOnlyPerson {

    private Name name;
    private Phone phones;
    private Email emails;
    private Address addresses;

    private final UniqueTagList tags;
    /**
     * Assumption: Every field must be present and not null.
     */
    public Person(Name name, Phone phones, Email emails, Address addresses, UniqueTagList tags) {
        this.name = name;
        this.phones = phones;
        this.emails = emails;
        this.addresses = addresses;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
    }

    /**
     * Copy constructor.
     */
    public Person(ReadOnlyPerson source) {
        this(source.getName(), source.getPhone(), source.getEmail(), source.getAddress(), source.getTags());
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Phone getPhone() {
        return phones;
    }

    @Override
    public Email getEmail() {
        return emails;
    }

    @Override
    public Address getAddress() {
        return addresses;
    }

    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }

    /**
     * Replaces this person's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyPerson // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyPerson) other));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phones, emails, addresses, tags);
    }

    @Override
    public String toString() {
        return getAsTextShowAll();
    }

    
    public void changePhones(String[] phones) throws IllegalValueException {
        final boolean isPrivate = this.phones.isPrivate();
        this.phones = new Phone(phones, isPrivate);
    }
    
    
    public void changePhonePrivacy(boolean isPrivate) {
        this.phones.setIsPrivate(isPrivate);
    }
    
    
    public void changeEmail(String[] emails) throws IllegalValueException {
        final boolean isPrivate = this.emails.isPrivate();
        this.emails = new Email(emails, isPrivate);
    }
    
    
    public void changeEmailPrivacy(boolean isPrivate) {
        this.emails.setIsPrivate(isPrivate);
    }
    
    
    public void changeAddresses(String[] addresses) throws IllegalValueException {
        final boolean isPrivate = this.addresses.isPrivate();
        this.addresses = new Address(addresses, isPrivate);
    }
    
    
    public void changeAddressPrivacy(boolean isPrivate) {
        this.addresses.setIsPrivate(isPrivate);
    }
}
