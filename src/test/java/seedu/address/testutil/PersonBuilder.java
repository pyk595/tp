package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.model.contactedinfo.ContactedInfo;
import seedu.address.model.date.BirthDate;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderList;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_BIRTHDATE = "2000-01-01";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private ArrayList<ContactedInfo> contactedInfo;
    private BirthDate birthDate;
    private Set<Tag> tags;
    private ReminderList reminderList;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        birthDate = BirthDate.parse(DEFAULT_BIRTHDATE);
        contactedInfo = new ArrayList<>();
        tags = new HashSet<>();
        reminderList = new ReminderList();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        birthDate = personToCopy.getBirthDate();
        contactedInfo = new ArrayList<>(personToCopy.getContactedInfoList());
        tags = new HashSet<>(personToCopy.getTags());
        reminderList = new ReminderList(personToCopy.getReminderList());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and add it to the tag set of {@code Person}
     * that we are building.
     *
     * @param tags the tags to add to the tag set.
     * @return this {@code PersonBuilder}.
     */
    public PersonBuilder addTags(String ... tags) {
        Set<Tag> newSet = new HashSet<>(this.tags);
        newSet.addAll(SampleDataUtil.getTagSet(tags));
        this.tags = newSet;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and deletes them from the tag set of {@code Person}
     * that we are building.
     *
     * @param tags the tags to delete from the tag set.
     * @return this {@code PersonBuilder}.
     */
    public PersonBuilder deleteTags(String ... tags) {
        Set<Tag> newSet = new HashSet<>(this.tags);
        newSet.removeAll(SampleDataUtil.getTagSet(tags));
        this.tags = newSet;
        return this;
    }

    /**
     * Sets the {@code ReminderList} of the {@code Person} that we are building.
     */
    public PersonBuilder withReminders(Reminder ... reminders) {
        ReminderList reminderList = new ReminderList();

        for (Reminder reminder : reminders) {
            reminderList.add(reminder);
        }
        return this;
    }

    /**
     * Adds the {@code Reminder} to the {@code Person} that we are building.
     *
     * @param reminder the reminder to add to the ReminderList
     * @return this {@code PersonBuilder}
     */
    public PersonBuilder addReminder(Reminder reminder) {
        this.reminderList.add(reminder);
        return this;
    }

    /**
     * Deletes the {@code Reminder} from the {@code Person} that we are building.
     *
     * @param index of the reminder to delete from the ReminderList
     * @return this {@code PersonBuilder}
     */
    public PersonBuilder deleteReminder(Index index) {
        this.reminderList.delete(index);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Parses the {@code ContactedInfo} into a {@code ArrayList<ContactedInfo>}
     * and add it to the ContactedInfo ArrayList of {@code Person}
     * that we are building.
     *
     * @param contactedInfo the contacted info to add to the contacted info set.
     * @return this {@code PersonBuilder}.
     */
    public PersonBuilder addContactedInfo(String ... contactedInfo) {
        ArrayList<ContactedInfo> contactedInfoList = new ArrayList<>(this.contactedInfo);
        contactedInfoList.addAll(SampleDataUtil.getContactedInfoList(contactedInfo));
        Collections.sort(contactedInfoList);
        this.contactedInfo = contactedInfoList;
        return this;
    }

    /**
     * Parses the {@code ContactedInfo} into a {@code ArrayList<ContactedInfo>} and
     * deletes them from the contacted information list of {@code Person}
     * that we are building.
     *
     * @param contactedInfoIndex the index of the contacted information to delete from the list.
     * @return this {@code PersonBuilder}.
     */
    public PersonBuilder deleteContactedInfo(Index contactedInfoIndex) {
        ArrayList<ContactedInfo> contactedInfoList = new ArrayList<>(contactedInfo);
        int index = contactedInfoIndex.getZeroBased();
        contactedInfoList.remove(index);
        this.contactedInfo = contactedInfoList;

        return this;
    }

    /**
     * Returns the default contacted info.
     *
     * @return this {@code PersonBuilder}.
     */
    public PersonBuilder addDefaultContactedInfo() {
        ArrayList<ContactedInfo> contactedInfoList = new ArrayList<>(this.contactedInfo);
        contactedInfoList.add(ContactedInfo.getDefaultContactedInfo());
        Collections.sort(contactedInfoList);
        this.contactedInfo = contactedInfoList;
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code BirthDate} of the {@code Person} that we are building.
     *
     * @param birthDate the String to be used as a date.
     * @return the PersonBuilder.
     */
    public PersonBuilder withBirthDate(String birthDate) {
        this.birthDate = BirthDate.parse(birthDate);
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, address, birthDate, contactedInfo, tags, reminderList);
    }

}
