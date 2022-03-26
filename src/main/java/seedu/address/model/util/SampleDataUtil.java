package seedu.address.model.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.contactedinfo.ContactedInfo;
import seedu.address.model.date.BirthDate;
import seedu.address.model.date.RecentDate;
import seedu.address.model.description.Description;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.reminder.ReminderList;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"),
                BirthDate.parse("2000-01-01"),
                getContactedInfoList("2020-01-02 Meet up", "2021-01-02 Phone call"),
                getTagSet("friends"), new ReminderList()),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                BirthDate.parse("2000-01-02"),
                getContactedInfoList("2020-01-02 Meet up", "2021-01-02 Phone call"),
                getTagSet("colleagues", "friends"), new ReminderList()),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                BirthDate.parse("2000-03-01"),
                getContactedInfoList("2020-01-02 Meet up", "2021-01-02 Phone call"),
                getTagSet("neighbours"), new ReminderList()),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                BirthDate.parse("2000-01-04"),
                getContactedInfoList("2020-01-02 Meet up", "2021-01-02 Phone call"),
                getTagSet("family"), new ReminderList()),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                BirthDate.parse("2000-05-01"),
                getContactedInfoList("2020-01-02 Meet up", "2021-01-02 Phone call"),
                getTagSet("classmates"), new ReminderList()),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                BirthDate.parse("2006-07-01"),
                getContactedInfoList("2020-01-02 Meet up", "2021-01-02 Phone call"),
                getTagSet("colleagues"), new ReminderList())
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }


    /**
     * Returns a tag set containing the list of strings given.
     */
    public static ArrayList<ContactedInfo> getContactedInfoList(String... strings) {
        return (ArrayList<ContactedInfo>) Arrays.stream(strings)
                .map((str) -> {
                    String[] strArr = str.split("\\s+", 2);
                    RecentDate recentDate = RecentDate.parse(strArr[0]);
                    Description description = new Description(strArr[1]);
                    return new ContactedInfo(recentDate, description);
                })
                .collect(Collectors.toList());
    }
}
