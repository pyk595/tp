package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.person.Email.VALIDATION_REGEX;

/**
 * Represents a Person's description in the Contacted Date region.
 * Guarantees: immutable; is always valid
 */
public class Description {
    public final String value;

    public Description(String desc) {
        requireNonNull(desc);
        value = desc;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Description // instanceof handles nulls
                && value.equals(((Description) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
