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

class PersonContainsTagPredicateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new PersonContainsTagPredicate(null));
    }

    @Test
    public void test_personContainsTag_returnsTrue() {
        // one tag
        PersonContainsTagPredicate predicate = new PersonContainsTagPredicate(new Tag(VALID_TAG_FRIEND));
        assertTrue(predicate.test(new PersonBuilder(ALICE).withTags(VALID_TAG_FRIEND).build()));

        // multiple tag
        predicate = new PersonContainsTagPredicate(new Tag(VALID_TAG_FRIEND));
        assertTrue(predicate.test(new PersonBuilder(ALICE).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build()));

        // mixed case
        predicate = new PersonContainsTagPredicate(new Tag("WIfe"));
        assertTrue(predicate.test(new PersonBuilder(ALICE).withTags("wiFE", VALID_TAG_HUSBAND).build()));
    }

    @Test
    public void test_personDoesNotContainsTag_returnsFalse() {
        // non-matching
        PersonContainsTagPredicate predicate = new PersonContainsTagPredicate(new Tag("randomTag123"));
        assertFalse(predicate.test(new PersonBuilder(ALICE).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND).build()));
    }

    @Test
    void equals() {
        Tag firstTag = new Tag("firstTag");
        Tag secondTag = new Tag("secondTag");

        PersonContainsTagPredicate firstPredicate = new PersonContainsTagPredicate(firstTag);
        PersonContainsTagPredicate secondPredicate = new PersonContainsTagPredicate(secondTag);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PersonContainsTagPredicate firstPredicateCopy = new PersonContainsTagPredicate(firstTag);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }
}
