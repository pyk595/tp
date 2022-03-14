package seedu.address.model.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RecentDate extends DocumentedDate {
    private static final DateTimeFormatter FORMATTER_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public final String value;

    /**
     * Constructs a {@code RecentDate}.
     *
     * @param date A non null LocalDate object.
     */
    public RecentDate(LocalDate date) {
        super(date);
        value = date.format(FORMATTER_INPUT);
    }

    /**
     * Creates a new {@code RecentDate} using a String.
     *
     * @param parsedDate a String to be converted into a date.
     * @return A non null {@code RecentDate}.
     */
    public static RecentDate parse(String parsedDate) {
        LocalDate date = LocalDate.parse(parsedDate);
        return new RecentDate(date);
    }

    /**
     * Returns today's date in String format.
     *
     * @return today's date in String format.
     */
    public static String defaultRecentDateInStr() {
        return LocalDate.now().format(FORMATTER_INPUT);
    }

    /**
     * Returns today's date
     *
     * @return today's date {@code RecentDate}.
     */
    public static RecentDate defaultRecentDate() {
        return new RecentDate(LocalDate.now());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RecentDate)) {
            return false;
        }

        // state check
        return value.equals(((RecentDate) other).value);
    }
}
