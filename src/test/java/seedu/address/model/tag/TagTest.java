package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void isSameTagIgnoreCase_notSameTagOrNull_returnsFalse() {
        assertFalse(new Tag("tiger").isSameTagIgnoreCase(new Tag("lion")));
        assertFalse(new Tag("tiger").isSameTagIgnoreCase(null));
    }

    @Test
    public void isSameTagIgnoreCase_sameTag_returnsTrue() {
        assertTrue(new Tag("TiGeR").isSameTagIgnoreCase(new Tag("tIgEr")));
        assertTrue(new Tag("Tiger").isSameTagIgnoreCase(new Tag("Tiger")));
        Tag tag = new Tag("lion");
        assertTrue(tag.isSameTagIgnoreCase(tag));
    }
}
