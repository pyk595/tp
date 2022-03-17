package seedu.address.model.reminder;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import seedu.address.model.date.DocumentedDate;
import seedu.address.model.date.ReminderDate;

/**
 * Represents a reminder given to a contact in the address book.
 */
public class Reminder implements Comparable<Reminder> {

    public static final String MESSAGE_CONSTRAINTS = "Reminder descriptions should be alphanumeric";
    private static final Reminder EMPTY_REMINDER = new Reminder(new ReminderDescription("Default reminder"),
            new ReminderDate(LocalDate.now()));

    private final ReminderDescription reminderDescription;
    private final ReminderDate date;

    /**
     * Constructs a {@code Reminder}
     *
     * @param reminderDescription Description of the reminder
     * @param date the date which the reminder is on
     */
    public Reminder(ReminderDescription reminderDescription, ReminderDate date) {
        requireNonNull(reminderDescription);
        requireNonNull(date);
        this.date = date;
        this.reminderDescription = reminderDescription;
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

    @Override
    public int compareTo(Reminder otherReminder) {
        if (this.isSameDateAs(otherReminder.date)) {
            return this.reminderDescription.compareTo(otherReminder.reminderDescription);
        } else {
            return this.date.compareTo(otherReminder.date);
        }
    }

    public ReminderDescription getDescription() {
        return reminderDescription;
    }

    @Override
    public String toString() {
        return String.format("%1$s(%2$s)", this.reminderDescription.toString(), this.date.toString());
    }
}
