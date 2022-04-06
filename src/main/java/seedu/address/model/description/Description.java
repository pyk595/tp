package seedu.address.model.description;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.commons.util.StringUtil;

/**
 * Represents a Person's description in the Contacted Date region.
 * Guarantees: immutable; is always valid
 */
public class Description {

    public static final String MESSAGE_CONSTRAINTS =
            "Description can take any values, but must be within 280 characters and should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code Description}.
     *
     * @param desc A valid description.
     */
    public Description(String desc) {
        requireNonNull(desc);
        checkArgument(isValidDescription(desc), MESSAGE_CONSTRAINTS);
        value = desc;
    }

    /**
     * Returns a {@code Description} containing the string "First Interaction".
     *
     * @return a default {@code Description}.
     */
    public static Description defaultDesc() {
        return new Description("First Interaction");
    }

    /**
     * Returns the String representation of the {@code Description}.
     *
     * @return the {@code Description} in String.
     */
    @Override
    public String toString() {
        return value;
    }

    /**
     * Checks if the parsed String is a valid input.
     *
     * @return true if the input String matches the validation regex
     * and if the length of the String does not exceed 280 characters,
     * otherwise false.
     */
    public static boolean isValidDescription(String test) {
        return test.matches(VALIDATION_REGEX)
                && StringUtil.isValidDescriptionLength(test);
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && value.equalsIgnoreCase(((Description) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
