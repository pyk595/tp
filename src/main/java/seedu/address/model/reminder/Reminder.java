package seedu.address.model.reminder;

import seedu.address.model.date.DocumentedDate;

import java.time.LocalDate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a reminder given to a contact in the address book.
 */
public class Reminder {
    private final String reminderDescription;
    private final DocumentedDate date;

    public static final String MESSAGE_CONSTRAINTS = "Reminder descriptions should be alphanumeric";
    public static final String VALIDATION_REGEX = "\\p{Alnum}+";

    private static final Reminder EMPTY_REMINDER = new Reminder("", new DocumentedDate(LocalDate.now()));

    /**
     * Constructs a {@code Reminder}
     * @param reminderDescription Description of the reminder
     * @param date the date which the reminder is on
     */
    public Reminder(String reminderDescription, DocumentedDate date) {
        requireNonNull(reminderDescription);
        checkArgument(isValidReminderDescription(reminderDescription), MESSAGE_CONSTRAINTS);
        requireNonNull(date);
        this.date = date;
        this.reminderDescription = reminderDescription;
    }

    /**
     * Returns true if a given string is a valid reminder description.
     */
    public static boolean isValidReminderDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if the reminder date is today.
     */
    public boolean isToday() {
        return date.isToday();
    }

    /**
     * Returns true if the reminder date is on the specified date.
     */
    public boolean isSameDateAs(DocumentedDate comparedDate) {
        return this.date.equals(comparedDate);
    }

    public static Reminder empty() {
        return EMPTY_REMINDER;
    }
}
