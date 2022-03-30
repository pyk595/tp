package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.testutil.PersonBuilder;

class PersonOutsideDateRangePredicateTest {

    @Test
    public void test_personOutsideDateRange_returnsTrue() {
        PersonOutsideDateRangePredicate predicate = new PersonOutsideDateRangePredicate(0);
        assertTrue(predicate.test(ALICE));
    }

    @Test
    public void test_personOutsideDateRange_returnsFalse() {
        PersonOutsideDateRangePredicate predicate = new PersonOutsideDateRangePredicate(0);
        assertFalse(predicate.test(new PersonBuilder(ALICE).addDefaultContactedInfo().build()));
    }

    @Test
    public void test_personWithNoContactedInfo_returnsTrue() {
        PersonOutsideDateRangePredicate predicate = new PersonOutsideDateRangePredicate(0);
        PersonOutsideDateRangePredicate secondPredicate =
                new PersonOutsideDateRangePredicate(Integer.MAX_VALUE - 1);
        Index index = Index.fromOneBased(1);
        assertTrue(predicate.test(new PersonBuilder(ALICE).deleteContactedInfo(index).build()));
        assertTrue(secondPredicate.test(new PersonBuilder(ALICE).deleteContactedInfo(index).build()));
    }

    @Test
    public void test_personWithNoContactedInfo_returnsFalse() {
        PersonOutsideDateRangePredicate predicate =
                new PersonOutsideDateRangePredicate(Integer.MAX_VALUE);
        Index index = Index.fromOneBased(1);
        assertFalse(predicate.test(new PersonBuilder(ALICE).deleteContactedInfo(index).build()));
    }

    @Test
    public void equals() {
        PersonOutsideDateRangePredicate firstPredicate = new PersonOutsideDateRangePredicate(0);
        PersonOutsideDateRangePredicate secondPredicate = new PersonOutsideDateRangePredicate(1);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonOutsideDateRangePredicate firstPredicateCopy = new PersonOutsideDateRangePredicate(0);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different predicate -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

}
