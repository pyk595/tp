package seedu.address.model.date;

import static java.time.temporal.ChronoUnit.DAYS;

import java.time.LocalDate;

/**
 * Represents a Date in the address book.
 */
public class Date {
    private LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param parsedDate A valid string to be parsed as a date.
     */
    public Date(String parsedDate) {
        this.date = LocalDate.parse(parsedDate);
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
        return (today.compareTo(date) == 0);
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
