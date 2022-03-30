package seedu.address.model.date;

import static java.time.temporal.ChronoUnit.DAYS;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RecentDate extends DocumentedDate implements Comparable<RecentDate> {

    private static final DateTimeFormatter FORMATTER_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public final String value;
    private LocalDate date;

    /**
     * Constructs a {@code RecentDate}.
     *
     * @param date A non null LocalDate object.
     */
    public RecentDate(LocalDate date) {
        super(date);
        this.date = date;
        value = date.format(FORMATTER_INPUT);
    }

    /**
     * Creates a new {@code RecentDate} using a String.
     *
     * @param parsedDate a String to be converted into a date.
     * @return A non null {@code RecentDate}.
     */
    public static RecentDate parse(String parsedDate) {
        checkArgument(isValidDate(parsedDate));
        LocalDate date = LocalDate.parse(parsedDate);
        return new RecentDate(date);
    }

    /**
     * Returns true if a given string can be converted to a date
     * that has either occurred today or in the past.
     */
    public static boolean isValidRecentDate(String test) {
        LocalDate testDate = LocalDate.parse(test);
        return DAYS.between(testDate, LocalDate.now()) >= 0;
    }

    /**
     * Returns today's date
     *
     * @return today's date {@code RecentDate}.
     */
    public static RecentDate defaultRecentDate() {
        return new RecentDate(LocalDate.now());
    }

    /**
     * compare two {@code RecentDate} objects
     *
     * @param rd RecentDate object to compare to.
     * @return an integer corresponding to which date comes first.
     */
    @Override
    public int compareTo(RecentDate rd) {
        return date.compareTo(rd.date) * -1;
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
