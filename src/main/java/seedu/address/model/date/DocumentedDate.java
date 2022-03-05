package seedu.address.model.date;

import seedu.address.commons.exceptions.IllegalValueException;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

/**
 * Represents a Date in the address book.
 */
public class DocumentedDate {
    private LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A non null LocalDate object.
     */
    public DocumentedDate(LocalDate date) throws IllegalValueException {
        requireNonNull(date);
        if (DAYS.between(date, LocalDate.now()) >= Integer.MAX_VALUE) {
            throw new IllegalValueException("Unrealistic date given");
        }
        this.date = date;
    }

    /**
     * Returns the number of days passed since the parsed date.
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
     * Returns a string representation of the {@code Date}.
     *
     * @return a string in the format of Day Month Year.
     */
    @Override
    public String toString() {
        return String.format("%d %s %d",
                date.getDayOfMonth(),
                date.getMonth(),
                date.getYear());
    }

}
