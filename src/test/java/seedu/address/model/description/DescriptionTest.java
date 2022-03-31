package seedu.address.model.description;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DescriptionTest {

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
