package seedu.address.model.reminder;

import static java.util.Objects.requireNonNull;

import seedu.address.model.date.ReminderDate;

/**
 * Represents a reminder given to a contact in the address book.
 */
public class Reminder implements Comparable<Reminder> {

    public static final String MESSAGE_CONSTRAINTS = "Reminder descriptions should be alphanumeric, reminder dates "
            + "should be in YYYY-MM-DD format, with zero-padding, and it should not be in the past.";

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
     * Checks if the {@code ReminderDate} is on the specified {@code ReminderDate}.
     *
     * @return true if the {@code ReminderDate} is on the specified {@code ReminderDate}, false otherwise.
     */
    public boolean isSameDateAs(ReminderDate reminderDate) {
        return this.date.compareTo(reminderDate) == 0;
    }

    @Override
    public int compareTo(Reminder otherReminder) {
        if (this.isSameDateAs(otherReminder.date)) {
            return this.reminderDescription.compareTo(otherReminder.reminderDescription);
        } else {
            return this.date.compareTo(otherReminder.date);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof Reminder
                && reminderDescription.equals(((Reminder) other).reminderDescription)
                && date.equals(((Reminder) other).date));
    }

    /**
     * Gets the {@code ReminderDescription}.
     *
     * @return the {@code ReminderDescription}
     */
    public ReminderDescription getDescription() {
        return reminderDescription;
    }

    @Override
    public String toString() {
        return String.format("%1$s (%2$s)", this.reminderDescription.toString(), this.date.toString());
    }

    /**
     * Gets the {@code ReminderDate}.
     *
     * @return the {@code ReminderDate}
     */
    public ReminderDate getReminderDate() {
        return this.date;
    }
}
