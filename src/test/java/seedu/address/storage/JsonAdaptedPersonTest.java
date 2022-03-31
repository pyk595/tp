package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.contactedinfo.ContactedInfo;
import seedu.address.model.date.BirthDate;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.reminder.Reminder;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_BIRTHDATE = "1/1/2000";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_RECENT_DATE = "hello world";
    private static final String INVALID_TAG = "#friend";
    private static final List<JsonAdaptedReminder> INVALID_REMINDER = new ArrayList<>(Arrays
            .asList(new JsonAdaptedReminder("meeting!", "2022-03-15")));

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_BIRTHDATE = BENSON.getBirthDate().value;
    private static final List<JsonAdaptedContactedInfo> VALID_CONTACTED_INFO = BENSON.getContactedInfoList().stream()
            .map(JsonAdaptedContactedInfo::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<JsonAdaptedReminder> VALID_REMINDERS = BENSON.getReminderList()
            .getPriorityQueue().stream()
            .map(JsonAdaptedReminder::new).collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        INVALID_NAME,
                        VALID_PHONE,
                        VALID_EMAIL,
                        VALID_ADDRESS,
                        VALID_BIRTHDATE,
                        VALID_CONTACTED_INFO,
                        VALID_TAGS,
                        VALID_REMINDERS
                );

        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        null,
                        VALID_PHONE,
                        VALID_EMAIL,
                        VALID_ADDRESS,
                        VALID_BIRTHDATE,
                        VALID_CONTACTED_INFO,
                        VALID_TAGS,
                        VALID_REMINDERS
                );

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME,
                        INVALID_PHONE,
                        VALID_EMAIL,
                        VALID_ADDRESS,
                        VALID_BIRTHDATE,
                        VALID_CONTACTED_INFO,
                        VALID_TAGS,
                        VALID_REMINDERS
                );

        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME,
                        null,
                        VALID_EMAIL,
                        VALID_ADDRESS,
                        VALID_BIRTHDATE,
                        VALID_CONTACTED_INFO,
                        VALID_TAGS,
                        VALID_REMINDERS
                );

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =

                new JsonAdaptedPerson(
                        VALID_NAME,
                        VALID_PHONE,
                        INVALID_EMAIL,
                        VALID_ADDRESS,
                        VALID_BIRTHDATE,
                        VALID_CONTACTED_INFO,
                        VALID_TAGS,
                        VALID_REMINDERS
                );

        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME,
                        VALID_PHONE,
                        null,
                        VALID_ADDRESS,
                        VALID_BIRTHDATE,
                        VALID_CONTACTED_INFO,
                        VALID_TAGS,
                        VALID_REMINDERS
                );

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME,
                        VALID_PHONE,
                        VALID_EMAIL,
                        INVALID_ADDRESS,
                        VALID_BIRTHDATE,
                        VALID_CONTACTED_INFO,
                        VALID_TAGS,
                        VALID_REMINDERS
                );

        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME,
                        VALID_PHONE,
                        VALID_EMAIL,
                        null,
                        VALID_BIRTHDATE,
                        VALID_CONTACTED_INFO,
                        VALID_TAGS,
                        VALID_REMINDERS
                );

        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidContactedInfoDate_throwsIllegalValueException() {
        List<JsonAdaptedContactedInfo> invalidDate = new ArrayList<>(VALID_CONTACTED_INFO);
        invalidDate.add(new JsonAdaptedContactedInfo(INVALID_RECENT_DATE, "Meet up"));

        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME,
                        VALID_PHONE,
                        VALID_EMAIL,
                        VALID_ADDRESS,
                        VALID_BIRTHDATE,
                        invalidDate,
                        VALID_TAGS,
                        VALID_REMINDERS
                );
        String expectedMessage = ContactedInfo.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidContactedDescription_throwsIllegalValueException() {
        List<JsonAdaptedContactedInfo> invalidDesc = new ArrayList<>(VALID_CONTACTED_INFO);
        invalidDesc.add(new JsonAdaptedContactedInfo("2020-02-02", " "));

        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME,
                        VALID_PHONE,
                        VALID_EMAIL,
                        VALID_ADDRESS,
                        VALID_BIRTHDATE,
                        invalidDesc,
                        VALID_TAGS,
                        VALID_REMINDERS
                );
        String expectedMessage = ContactedInfo.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME,
                        VALID_PHONE,
                        VALID_EMAIL,
                        VALID_ADDRESS,
                        VALID_BIRTHDATE,
                        VALID_CONTACTED_INFO,
                        invalidTags,
                        VALID_REMINDERS
                );
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_nullBirthDate_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME,
                        VALID_PHONE,
                        VALID_EMAIL,
                        VALID_ADDRESS,
                        null,
                        VALID_CONTACTED_INFO,
                        VALID_TAGS,
                        VALID_REMINDERS
                );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, BirthDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidBirthDate_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME,
                        VALID_PHONE,
                        VALID_EMAIL,
                        VALID_ADDRESS,
                        INVALID_BIRTHDATE,
                        VALID_CONTACTED_INFO,
                        VALID_TAGS,
                        VALID_REMINDERS
                );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, BirthDate.class.getSimpleName());
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidReminder_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(
                        VALID_NAME,
                        VALID_PHONE,
                        VALID_EMAIL,
                        VALID_ADDRESS,
                        VALID_BIRTHDATE,
                        VALID_CONTACTED_INFO,
                        VALID_TAGS,
                        INVALID_REMINDER
                );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Reminder.class.getSimpleName());
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
