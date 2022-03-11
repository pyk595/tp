package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
/**
 * Represents a Person's description in the Contacted Date region.
 * Guarantees: immutable; is always valid
 */
public class Description {

    public final String value;

    /**
     * Constructs an {@code Description}.
     *
     * @param desc A valid description.
     */
    public Description(String desc) {
        requireNonNull(desc);
        value = desc;
    }

    /**
     * Returns String representation of description.
     */
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
