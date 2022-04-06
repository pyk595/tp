package seedu.address.model.date;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ReminderDate extends DocumentedDate implements Comparable<ReminderDate> {

    public static final String MESSAGE_CONSTRAINTS = "Reminder Dates cannot be in the past!";
    private static final DateTimeFormatter FORMATTER_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
    public final String value;

    /**
     * Constructs a {@code DocumentedDate}.
     *
     * @param date A non null LocalDate object.
     */
    public ReminderDate(LocalDate date) {
        super(date);
        value = date.format(FORMATTER_INPUT);
    }

    /**
     * Creates a new {@code ReminderDate} using a String.
     *
     * @param parsedDate a String to be converted into a date.
     * @return A non null {@code ReminderDate}.
     */
    public static ReminderDate parse(String parsedDate) {
        checkArgument(isValidDate(parsedDate));
        LocalDate date = LocalDate.parse(parsedDate);
        return new ReminderDate(date);
    }

    @Override
    public int compareTo(ReminderDate reminderDate) {
        requireNonNull(reminderDate);

        return super.getDate().compareTo(reminderDate.getDate());
    }

    @Override
    public boolean equals(Object object) {
        // short circuit if same object
        if (object == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(object instanceof ReminderDate)) {
            return false;
        }

        // state check
        return value.equals(((ReminderDate) object).value);
    }
}
