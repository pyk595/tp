package seedu.address.model.person;

import seedu.address.model.date.DocumentedDate;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

public class Date {
    public static final String MESSAGE_CONSTRAINTS = "Date must be in the form of yyyy-mm-dd or yyyy-MMM-dd";

    /*y
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public static final String VALIDATION_FORMAT = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$";
    public static final String VALIDATION_FORMAT1 =
            "^((?!(?:[02468][^048]|[13579][^26])00-FEB-29)(?:19|[2-9]\\d)"
                    + "(?!(?:[02468][^048]|[13579][^26])-FEB-29)\\d\\d)-(?!FEB-3[01])"
                    + "(?!(?:Apr|Jun|Sep|Nov)-31)(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec)"
                    + "-(?!00)((?:[0-2][0-9]|3[01]))$";

    public final DocumentedDate value;

    /**
     * Constructs an {@code Address}.
     *
     * @param date A valid address.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDate(date), MESSAGE_CONSTRAINTS);
        value = new DocumentedDate(date);
    }

    /**
     * Returns true if a given string is a valid email.
     */
    public static boolean isValidDate(String test) {
        return test.matches(VALIDATION_REGEX) && (test.matches(VALIDATION_FORMAT) || test.matches(VALIDATION_FORMAT1));
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && value.toString().equals(((Date) other).value.toString())); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
