package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s latest {@code ContactedInfo} is outside a given range.
 */
public class PersonOutsideDateRangePredicate implements Predicate<Person> {
    private final int numberOfDays;

    /**
     * Constructs a {@code PersonOutsideDateRangePredicate} object.
     *
     * @param days the number of days used as a range for this predicate.
     */
    public PersonOutsideDateRangePredicate(int days) {
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
        return person.getContactedDateRange() > numberOfDays;
    }

    /**
     * Returns true if this {@code PersonOutsideDateRangePredicate} object is equal to the given {@code Object}.
     *
     * @param other the other {@code Object} to verify the equality.
     * @return true if this {@code PersonOutsideDateRangePredicate} object is equal to the given {@code Object}.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PersonOutsideDateRangePredicate // instanceof handles nulls
                && numberOfDays == (((PersonOutsideDateRangePredicate) other).numberOfDays)); // state check
    }
}
