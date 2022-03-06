package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person} is tagged to a {@code Tag}.
 */
public class PersonContainsTagPredicate implements Predicate<Person> {
    private final Tag tag;

    /**
     * Constructs a {@code PersonContainsTagPredicate} object.
     *
     * @param tag the {@code Tag} for this predicate to verify.
     */
    public PersonContainsTagPredicate(Tag tag) {
        requireNonNull(tag);
        this.tag = tag;
    }

    /**
     * Evaluates whether the specified {@code Person} is tagged to the {@code Tag} in this predicate.
     *
     * @param person the {@code Person} to evaluate.
     * @return true if the specified {@code Person} is tagged to the {@code Tag} in this predicate.
     */
    @Override
    public boolean test(Person person) {
        return person.hasTagIgnoreCase(this.tag);
    }

    /**
     * Returns true if this {@code PersonContainsTagPredicate} object is equal to the given {@code Object}.
     *
     * @param other the other {@code Object} to verify the equality.
     * @return true if this {@code PersonContainsTagPredicate} object is equal to the given {@code Object}.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonContainsTagPredicate // instanceof handles nulls
                && tag.equals(((PersonContainsTagPredicate) other).tag)); // state check
    }
}
