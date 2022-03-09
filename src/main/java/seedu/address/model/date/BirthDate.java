package seedu.address.model.date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BirthDate extends DocumentedDate {
    private static final DateTimeFormatter FORMATTER_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public final String value;

    /**
     * Constructs a {@code BirthDate}.
     *
     * @param date A non null LocalDate object.
     */
    public BirthDate(LocalDate date) {
        super(date);
        value = date.format(FORMATTER_INPUT);
    }

    /**
     * Creates a new {@code BirthDate} using a String.
     *
     * @param parsedDate a String to be converted into a date.
     * @return A non null {@code BirthDate}.
     */
    public static BirthDate parse(String parsedDate) {
        LocalDate date = LocalDate.parse(parsedDate);
        return new BirthDate(date);
    }

    /**
     * Returns true if the saved birthdate occurs today, false otherwise.
     *
     * @return a boolean checking if the saved birthdate occurs today.
     */
    @Override
    public boolean isToday() {
        LocalDate today = LocalDate.now();
        LocalDate savedDate = super.getDate();
        savedDate = savedDate.withYear(today.getYear());
        return savedDate.equals(today);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BirthDate)) {
            return false;
        }

        // state check
        return value.equals(((BirthDate) other).value);
    }
}
