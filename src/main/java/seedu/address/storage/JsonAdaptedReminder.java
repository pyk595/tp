package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.date.ReminderDate;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderDescription;

/**
 *  * Jackson-friendly version of {@link Reminder}.
 */
public class JsonAdaptedReminder {
    private final String description;
    private final String date;

    /**
     * Constructs a {@code JsonAdaptedReminder} with the given {@code ReminderDescription} and {@code ReminderDate}.
     */
    @JsonCreator
    public JsonAdaptedReminder(@JsonProperty("description") String description, @JsonProperty("date") String date) {
        this.date = date;
        this.description = description;
    }

    /**
     * Converts a given {@code Reminder} into this class for Jackson use.
     */
    public JsonAdaptedReminder(Reminder source) {
        date = source.getReminderDate().value;
        description = source.getDescription().toString();
    }

    /**
     * Converts this Jackson-friendly adapted {@code Reminder} object into the model's {@code Reminder} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted reminder.
     */
    public Reminder toModelType() throws IllegalValueException {
        if (!ReminderDate.isValidDate(date) || !(ReminderDescription.isValidDescription(description))) {
            throw new IllegalValueException(Reminder.MESSAGE_CONSTRAINTS);
        }

        ReminderDate reminderDate = ParserUtil.parseSavedReminderDate(date);
        ReminderDescription reminderDescription = ParserUtil.parseReminderDescription(description);

        return new Reminder(reminderDescription, reminderDate);
    }
}
