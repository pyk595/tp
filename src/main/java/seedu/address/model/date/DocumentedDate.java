package seedu.address.model.date;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a DocumentedDate in the address book.
 */
public class DocumentedDate {
    private final LocalDate date;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd");

    /**
     * Constructs a {@code DocumentedDate}.
     *
     * @param date A non null LocalDate object.
     */
    public DocumentedDate(String date) {
        requireNonNull(date);
        this.date = LocalDate.parse(date);
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
     * Returns true if the saved date occurs today, false otherwise.
     *
     * @return a boolean checking if the saved date occurs today.
     */
    public boolean isToday() {
        LocalDate today = LocalDate.now();
        return date.equals(today);
    }

    /**
     * Returns a string representation of the {@code DocumentedDate}.
     *
     * @return a string in the format of Day Month Year.
     */
    @Override
    public String toString() {
        return formatter.format(date);
    }

}
