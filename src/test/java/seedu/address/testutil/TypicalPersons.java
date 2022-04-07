package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DAVID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTH_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTH_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BIRTH_DATE_DAVID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DAVID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DAVID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DAVID;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.date.ReminderDate;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderDescription;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withBirthDate("2000-01-01")
            .addContactedInfo("2020-02-02 Wedding")
            .withReminders(new Reminder(new ReminderDescription("Meeting"),
                    new ReminderDate(LocalDate.of(2025, 1, 1))))
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withBirthDate("2000-01-01")
            .addContactedInfo("2020-03-02 Meetup", "2021-01-01 Wedding")
            .withReminders(new Reminder(new ReminderDescription("Meeting"),
                    new ReminderDate(LocalDate.of(2025, 1, 1))))
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street")
            .withBirthDate("2000-01-01")
            .withReminders(new Reminder(new ReminderDescription("Meeting"),
                    new ReminderDate(LocalDate.of(2025, 1, 1))))
            .addContactedInfo("2020-03-02 Meetup", "2021-01-01 Wedding").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withTags("friends")
            .withBirthDate("2000-01-01")
            .withReminders(new Reminder(new ReminderDescription("Meeting"),
                    new ReminderDate(LocalDate.of(2025, 1, 1))))
            .addContactedInfo("2020-03-02 Meetup", "2021-01-01 Wedding").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave")
            .withBirthDate("2000-01-01")
            .withReminders(new Reminder(new ReminderDescription("Meeting"),
                    new ReminderDate(LocalDate.of(2025, 1, 1))))
            .addContactedInfo("2020-03-02 Meetup", "2021-01-01 Wedding").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo")
            .withBirthDate("2000-01-01")
            .withReminders(new Reminder(new ReminderDescription("Meeting"),
                    new ReminderDate(LocalDate.of(2025, 1, 1))))
            .addContactedInfo("2020-03-02 Meetup", "2021-01-01 Wedding").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street")
            .withBirthDate("2000-01-01")
            .withReminders(new Reminder(new ReminderDescription("Meeting"),
                    new ReminderDate(LocalDate.of(2025, 1, 1))))
            .addContactedInfo("2020-03-02 Meetup", "2021-01-01 Wedding").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india")
            .withBirthDate("2000-01-01")
            .addContactedInfo("2020-02-02 Meetup", "2021-01-01 PhoneCall").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave")
            .withBirthDate("2000-01-01")
            .addContactedInfo("2020-02-02 Meetup", "2021-01-01 PhoneCall").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).withTags(VALID_TAG_FRIEND)
            .withBirthDate(VALID_BIRTH_DATE_AMY)
            .addContactedInfo(VALID_CONTACTED_DATE_AMY + " " + VALID_CONTACTED_DESC_AMY).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .addContactedInfo(VALID_CONTACTED_DATE_BOB + " " + VALID_CONTACTED_DESC_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .withBirthDate(VALID_BIRTH_DATE_BOB).build();
    public static final Person DAVID = new PersonBuilder().withName(VALID_NAME_DAVID).withPhone(VALID_PHONE_DAVID)
            .withEmail(VALID_EMAIL_DAVID).withAddress(VALID_ADDRESS_DAVID).withTags(VALID_TAG_FRIEND)
            .withBirthDate(VALID_BIRTH_DATE_DAVID).addDefaultContactedInfo().build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
