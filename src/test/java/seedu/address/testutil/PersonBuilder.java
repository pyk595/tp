package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.date.BirthDate;
import seedu.address.model.date.RecentDate;
import seedu.address.model.person.Address;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
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
    public static final String DEFAULT_RECENTDATE = "2020-02-02";
    public static final String DEFAULT_DESCRIPTION = "Meet up";
    public static final String DEFAULT_BIRTHDATE = "2000-01-01";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private RecentDate date;
    private Description description;
    private BirthDate birthDate;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        date = RecentDate.parse(DEFAULT_RECENTDATE);
        description = new Description(DEFAULT_DESCRIPTION);
        birthDate = BirthDate.parse(DEFAULT_BIRTHDATE);
        tags = new HashSet<>();
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
        date = personToCopy.getContactedDate();
        description = personToCopy.getContactedDesc();
        tags = new HashSet<>(personToCopy.getTags());
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
     * Sets the {@code Date} of the {@code Date} that we are building.
     */
    public PersonBuilder withDate(String date) {
        this.date = RecentDate.parse(date);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Description} that we are building.
     */
    public PersonBuilder withDescription(String description) {
        this.description = new Description(description);
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
        return new Person(name, phone, email, address, birthDate, date, description, tags);
    }

}
