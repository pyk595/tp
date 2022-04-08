package seedu.address.model.date;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Represents a DocumentedDate in the address book.
 */
public class DocumentedDate {
    public static final String MESSAGE_CONSTRAINTS = "Dates should be in the format of YYYY-MM-DD";
    private static final DateTimeFormatter FORMATTER_OUTPUT =
            DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH);
    private LocalDate date;

    /**
     * Constructs a {@code DocumentedDate}.
     *
     * @param date A non null LocalDate object.
     */
    public DocumentedDate(LocalDate date) {
        requireNonNull(date);
        this.date = date;
    }

    /**
     * Returns the number of days passed since the saved date.
     *
     * @return an int representing the number of days passed.
     */
    public int getDaysPassed() {
        LocalDate today = LocalDate.now();
        int days = (int) DAYS.between(date, today);
        return days;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        if (test.length() != 10) {
            return false;
        }
        if (test.charAt(0) == '-' || test.charAt(0) == '+') {
            return false;
        }
        try {
            LocalDate.parse(test);
        } catch (DateTimeParseException dte) {
            return false;
        }
        return true;
    }

    /**
     * Returns true if the saved date occurs today, false otherwise.
     *
     * @return a boolean checking if the saved date occurs today.
     */
    public boolean isToday() {
        LocalDate today = LocalDate.now();
        return date.equals(today);
    }

    /**
     * Gets saved date.
     *
     * @return saved date.
     */
    protected LocalDate getDate() {
        return this.date;
    }

    /**
     * Returns a string representation of the {@code DocumentedDate}.
     *
     * @return a string in the format of Day Month Year.
     */
    @Override
    public String toString() {
        return date.format(FORMATTER_OUTPUT);
    }

}
