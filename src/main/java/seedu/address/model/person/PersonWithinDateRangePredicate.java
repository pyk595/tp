package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s latest {@code ContactedInfo} is within a given range.
 */
public class PersonWithinDateRangePredicate implements Predicate<Person> {
    private final int numberOfDays;

    /**
     * Constructs a {@code PersonWithinDateRangePredicate} object.
     *
     * @param days the number of days used as a range for this predicate.
     */
    public PersonWithinDateRangePredicate(int days) {
        assert days >= 0 : "days should not be less than 0";
        numberOfDays = days;
    }
    /**
     * Evaluates this predicate on the given argument.
     *
     * @param person the input argument
     * @return {@code true} if the input argument matches the predicate,
     * otherwise {@code false}
     */
    @Override
    public boolean test(Person person) {
        return person.getContactedDateRange() <= numberOfDays;
    }

    /**
     * Returns true if this {@code PersonWithinDateRangePredicate} object is equal to the given {@code Object}.
     *
     * @param other the other {@code Object} to verify the equality.
     * @return true if this {@code PersonWithinDateRangePredicate} object is equal to the given {@code Object}.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonWithinDateRangePredicate // instanceof handles nulls
                && numberOfDays == (((PersonWithinDateRangePredicate) other).numberOfDays)); // state check
    }
}
