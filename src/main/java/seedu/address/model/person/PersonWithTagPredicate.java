package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person} is tagged to a {@code Tag}.
 */
public class PersonWithTagPredicate implements Predicate<Person> {
    private final Tag tag;

    /**
     * Constructs a {@code PersonWithTagPredicate} object.
     *
     * @param tag the {@code Tag} for this predicate to verify.
     */
    public PersonWithTagPredicate(Tag tag) {
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
        return person.hasTag(this.tag);
    }

    /**
     * Returns true if this {@code PersonWithTagPredicate} object is equal to the given {@code Object}.
     *
     * @param other the other {@code Object} to verify the equality.
     * @return true if this {@code PersonWithTagPredicate} object is equal to the given {@code Object}.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonWithTagPredicate // instanceof handles nulls
                && tag.equals(((PersonWithTagPredicate) other).tag)); // state check
    }
}
