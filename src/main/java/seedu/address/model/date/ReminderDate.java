package seedu.address.model.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ReminderDate extends DocumentedDate implements Comparable<ReminderDate> {

    private static final DateTimeFormatter FORMATTER_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
        LocalDate date = LocalDate.parse(parsedDate);
        return new ReminderDate(date);
    }

    @Override
    public int compareTo(ReminderDate reminderDate) {

        if (reminderDate == null) {
            throw new NullPointerException("Reminder date compared is null.");
        }

        if (super.getDate().isBefore(reminderDate.getDate())) {
            return -1;
        } else if (super.getDate().isAfter(reminderDate.getDate())) {
            return 1;
        } else {
            return 0;
        }
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
