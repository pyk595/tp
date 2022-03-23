package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void compareTo() {
        Tag firstTag = new Tag("apple");
        Tag secondTag = new Tag("banana");
        Tag thirdTag = new Tag("BANANA");
        Tag fourthTag = new Tag("cat");

        assertTrue(secondTag.compareTo(thirdTag) == 0);
        assertTrue(secondTag.compareTo(firstTag) > 0);
        assertTrue(firstTag.compareTo(secondTag) < 0);
        assertTrue(firstTag.compareTo(fourthTag) < 0);
        assertTrue(fourthTag.compareTo(firstTag) > 0);
        assertTrue(firstTag.compareTo(firstTag) == 0);
    }

    @Test
    public void equals() {
        Tag firstTag = new Tag("first");
        Tag secondTag = new Tag("second");

        // same values -> returns true
        assertTrue(new Tag(VALID_TAG_FRIEND).equals(new Tag(VALID_TAG_FRIEND)));

        // same object -> returns true
        assertTrue(firstTag.equals(firstTag));

        // null -> returns false
        assertFalse(new Tag(VALID_TAG_FRIEND).equals(null));

        // different type -> returns false
        assertFalse(firstTag.equals(5));

        // different person -> returns false
        assertFalse(firstTag.equals(secondTag));

        // not equal
        assertFalse(new Tag(VALID_TAG_FRIEND).equals(new Tag(VALID_TAG_HUSBAND)));

        // equal (case insensitive)
        assertTrue(new Tag("TiGeR").equals(new Tag("tIgEr")));
        assertTrue(new Tag("Tiger").equals(new Tag("Tiger")));
    }
}
