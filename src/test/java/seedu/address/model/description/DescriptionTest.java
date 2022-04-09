package seedu.address.model.description;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        // null
        assertThrows(NullPointerException.class, () -> Description.isValidDescription(null));

        // invalid description
        assertFalse(Description.isValidDescription("")); // empty string
        assertFalse(Description.isValidDescription(" ")); // spaces only

        // valid description
        assertTrue(Description.isValidDescription("Wedding"));
        assertTrue(Description.isValidDescription("Phone Call!"));
    }

    @Test
    public void equals() {
        Description desc = new Description("Meet up");

        // same object -> returns true
        assertTrue(desc.equals(desc));

        // same values -> returns true
        Description descCopy = new Description("Meet up");
        assertTrue(desc.equals(descCopy));

        // different types -> returns false
        assertFalse(desc.equals(1));

        // null -> returns false
        assertFalse(desc.equals(null));

        // different remark -> returns false
        Description differentDesc = new Description("Phone call");
        assertFalse(desc.equals(differentDesc));
    }
}
