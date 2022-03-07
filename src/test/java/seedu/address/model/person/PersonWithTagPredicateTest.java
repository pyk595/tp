package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

class PersonWithTagPredicateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonWithTagPredicate(null));
    }

    @Test
    public void test_personWithTag_returnsTrue() {
        // one tag
        PersonWithTagPredicate predicate = new PersonWithTagPredicate(new Tag(VALID_TAG_FRIEND));
        assertTrue(predicate.test(new PersonBuilder(ALICE).withTags(VALID_TAG_FRIEND).build()));

        // multiple tag
        predicate = new PersonWithTagPredicate(new Tag(VALID_TAG_FRIEND));
        assertTrue(predicate.test(new PersonBuilder(ALICE).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build()));

        // mixed case
        predicate = new PersonWithTagPredicate(new Tag("WIfe"));
        assertTrue(predicate.test(new PersonBuilder(ALICE).withTags("wiFE", VALID_TAG_HUSBAND).build()));
    }

    @Test
    public void test_personWithoutTag_returnsFalse() {
        // non-matching
        PersonWithTagPredicate predicate = new PersonWithTagPredicate(new Tag("randomTag123"));
        assertFalse(predicate.test(new PersonBuilder(ALICE).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build()));
    }

    @Test
    void equals() {
        Tag firstTag = new Tag("firstTag");
        Tag secondTag = new Tag("secondTag");

        PersonWithTagPredicate firstPredicate = new PersonWithTagPredicate(firstTag);
        PersonWithTagPredicate secondPredicate = new PersonWithTagPredicate(secondTag);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonWithTagPredicate firstPredicateCopy = new PersonWithTagPredicate(firstTag);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
