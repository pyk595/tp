package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.testutil.PersonBuilder;

public class PersonWithinDateRangePredicateTest {

    @Test
    public void test_personWithinDateRange_returnsFalse() {
        PersonWithinDateRangePredicate predicate = new PersonWithinDateRangePredicate(0);
        assertFalse(predicate.test(ALICE));
    }

    @Test
    public void test_personWithinDateRange_returnsTrue() {
        PersonWithinDateRangePredicate predicate = new PersonWithinDateRangePredicate(0);
        assertTrue(predicate.test(new PersonBuilder(ALICE).addDefaultContactedInfo().build()));
    }

    @Test
    public void test_personWithinDateBoundary_returnsTrue() {
        PersonWithinDateRangePredicate predicate = new PersonWithinDateRangePredicate(1);
        assertTrue(predicate.test(new PersonBuilder(ALICE).addDefaultContactedInfo().build()));
    }

    @Test
    public void test_personOutsideDateBoundary_returnsFalse() {
        PersonWithinDateRangePredicate predicate = new PersonWithinDateRangePredicate(Integer.MAX_VALUE - 1);
        Index index = Index.fromOneBased(1);
        assertFalse(predicate.test(new PersonBuilder(ALICE).deleteContactedInfo(index).build()));
    }

    @Test
    public void test_personWithNoContactedInfo_returnsFalse() {
        PersonWithinDateRangePredicate predicate = new PersonWithinDateRangePredicate(0);
        Index index = Index.fromOneBased(1);
        assertFalse(predicate.test(new PersonBuilder(ALICE).deleteContactedInfo(index).build()));
    }

    @Test
    public void test_personWithNoContactedInfo_returnsTrue() {
        PersonWithinDateRangePredicate predicate =
                new PersonWithinDateRangePredicate(Integer.MAX_VALUE);
        Index index = Index.fromOneBased(1);
        assertTrue(predicate.test(new PersonBuilder(ALICE).deleteContactedInfo(index).build()));
    }

    @Test
    public void equals() {
        PersonWithinDateRangePredicate firstPredicate = new PersonWithinDateRangePredicate(0);
        PersonWithinDateRangePredicate secondPredicate = new PersonWithinDateRangePredicate(1);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonWithinDateRangePredicate firstPredicateCopy = new PersonWithinDateRangePredicate(0);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
