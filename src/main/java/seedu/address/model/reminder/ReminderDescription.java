package seedu.address.model.reminder;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * The description of a Reminder object.
 */
public class ReminderDescription implements Comparable<ReminderDescription> {
    public static final String MESSAGE_CONSTRAINTS =
            "Descriptions should only contain alphanumeric characters and spaces, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    public final String description;

    /**
     * Constructs a {@code ReminderDescription}.
     *
     * @param description A valid description.
     */
    public ReminderDescription(String description) {
        requireNonNull(description);
        checkArgument(isValidDescription(description), MESSAGE_CONSTRAINTS);
        this.description = description;
    }

    /**
     * Checks if the given {@code String} is a valid {@code ReminderDescription}.
     *
     * @return true if a given {@code String} is a valid {@code ReminderDescription}, false otherwise.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return description;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReminderDescription // instanceof handles nulls
                && description.equalsIgnoreCase(((ReminderDescription) other).description)); // state check
    }

    @Override
    public int hashCode() {
        return description.hashCode();
    }

    @Override
    public int compareTo(ReminderDescription otherReminderDescription) {
        return this.description.compareToIgnoreCase(otherReminderDescription.description);
    }
}
