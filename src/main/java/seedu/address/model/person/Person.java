package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.model.contactedinfo.ContactedInfo;
import seedu.address.model.date.BirthDate;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderList;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final BirthDate birthDate;
    private final List<ContactedInfo> contactedInfoList;
    private final Set<Tag> tags = new HashSet<>();
    private final ReminderList reminderList;

    /**
     * Every field must be present and not null. A constructor including the reminder.
     * @param name of the person
     * @param phone number of the person
     * @param email of the person
     * @param address of the person
     * @param birthDate of the person
     * @param contactedInfoList of the person
     * @param tags of the person
     * @param reminderList associated with this person
     */
    public Person(Name name, Phone phone, Email email, Address address, BirthDate birthDate,
                  List<ContactedInfo> contactedInfoList, Set<Tag> tags, ReminderList reminderList) {
        requireAllNonNull(name, phone, email, address, birthDate, contactedInfoList, tags, reminderList);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
        this.contactedInfoList = contactedInfoList;
        this.tags.addAll(tags);
        this.reminderList = reminderList;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public BirthDate getBirthDate() {
        return birthDate;
    }

    public List<ContactedInfo> getContactedInfoList() {
        return Collections.unmodifiableList(contactedInfoList);
    }

    public int getContactedInfoListSize() {
        return Collections.unmodifiableList(contactedInfoList).size();
    }

    public Optional<ContactedInfo> getLatestContactedInfoEntry() {
        return contactedInfoList.size() == 0 ? Optional.empty() : Optional.of(contactedInfoList.get(0));
    }

    public Integer getContactedDateRange() {
        return getLatestContactedInfoEntry()
                .map(ContactedInfo::getDaysPassed)
                .orElse(Integer.MAX_VALUE);
    }


    public boolean isBirthdayToday() {
        return this.birthDate.isToday();
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public ReminderList getReminderList() {
        return reminderList.getCopy();
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if this {@code Person} is tagged to the specified {@code Tag} (case-insensitive).
     *
     * @param tag the {@code Tag} to verify whether it is tagged to this {@code Person}
     * @return true if this {@code Person} is tagged to the specified {@code Tag}.
     */
    public boolean hasTag(Tag tag) {
        return (tag != null)
                && this.tags.contains(tag);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getBirthDate().equals(getBirthDate())
                && otherPerson.getContactedInfoList().equals(getContactedInfoList())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, contactedInfoList, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; birthday: ")
                .append(getBirthDate())
                .append("; Last Contacted: ")
                .append(getLatestContactedInfoEntry()
                        .map(ContactedInfo::toString)
                        .orElse("No entry yet"));

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Checks if the {@code ReminderList} contains the {@code Reminder} object.
     *
     * @param reminder to be checked
     * @return true if the {@code ReminderList} contains the {@code Reminder}, false otherwise
     */
    public boolean containsReminder(Reminder reminder) {
        return this.reminderList.containsReminder(reminder);
    }
}
