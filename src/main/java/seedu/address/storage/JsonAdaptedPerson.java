package seedu.address.storage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.contactedinfo.ContactedInfo;
import seedu.address.model.date.BirthDate;
import seedu.address.model.date.DocumentedDate;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.reminder.ReminderList;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String birthDate;

    private final List<JsonAdaptedContactedInfo> contactedInfo = new ArrayList<>();
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedReminder> reminders = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("birthDate") String birthDate,
            @JsonProperty("contactedInfo") List<JsonAdaptedContactedInfo> contactedInfo,
            @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("reminders") List<JsonAdaptedReminder> reminders) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthDate = birthDate;
        if (contactedInfo != null) {
            this.contactedInfo.addAll(contactedInfo);
        }
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (reminders != null) {
            this.reminders.addAll(reminders);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        birthDate = source.getBirthDate().value;
        contactedInfo.addAll(source.getContactedInfoList().stream()
                .map(JsonAdaptedContactedInfo::new)
                .collect(Collectors.toList()));
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        reminders.addAll(source.getReminderList()
                .getPriorityQueue()
                .stream()
                .map(JsonAdaptedReminder::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        final List<ContactedInfo> personContactedInfo = new ArrayList<>();
        for (JsonAdaptedContactedInfo eachContactedInfo : contactedInfo) {
            personContactedInfo.add(eachContactedInfo.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (birthDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                                                            BirthDate.class.getSimpleName()));
        }
        if (!DocumentedDate.isValidDate(birthDate)) {
            throw new IllegalValueException(DocumentedDate.MESSAGE_CONSTRAINTS);
        }
        final BirthDate modelBirthDate = BirthDate.parse(birthDate);

        final ArrayList<ContactedInfo> modelContactedInfo = new ArrayList<>(personContactedInfo);
        Collections.sort(modelContactedInfo);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        final ReminderList modelReminderList = new ReminderList();

        for (JsonAdaptedReminder jsonAdaptedReminder : reminders) {
            modelReminderList.add(jsonAdaptedReminder.toModelType());
        }

        return new Person(modelName, modelPhone, modelEmail,
                modelAddress, modelBirthDate, modelContactedInfo, modelTags, modelReminderList);
    }

}
