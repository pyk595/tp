package seedu.address.model.contactedinfo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DESC_BOB;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ContactedInfoTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ContactedInfo(null, "meetup"));
        assertThrows(NullPointerException.class, () -> new ContactedInfo("2020-02-02", null));
        assertThrows(NullPointerException.class, () -> new ContactedInfo(null, null));
    }

    @Test
    public void constructor_invalidDescription_throwsIllegalArgumentException() {
        String description = "";
        assertThrows(IllegalArgumentException.class, () -> new ContactedInfo("2020-02-02", description));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String date = "not a date";
        assertThrows(IllegalArgumentException.class, () -> new ContactedInfo(date, "meet up"));
    }

    @Test
    public void equals() {
        ContactedInfo first = new ContactedInfo("2020-01-02", "phone call");
        ContactedInfo second = new ContactedInfo("2021-02-01", "meeting");

        // same values -> returns true
        assertTrue(new ContactedInfo(VALID_CONTACTED_DATE_AMY, VALID_CONTACTED_DESC_AMY).equals(
                new ContactedInfo(VALID_CONTACTED_DATE_AMY, VALID_CONTACTED_DESC_AMY)));

        // same object -> returns true
        assertTrue(first.equals(first));

        // null -> returns false
        assertFalse(new ContactedInfo(VALID_CONTACTED_DATE_AMY, VALID_CONTACTED_DESC_AMY).equals(null));

        // different type -> returns false
        assertFalse(first.equals(5));

        // different person -> returns false
        assertFalse(first.equals(second));

        // not equal
        assertFalse(new ContactedInfo(VALID_CONTACTED_DATE_AMY, VALID_CONTACTED_DESC_AMY).equals(
                new ContactedInfo(VALID_CONTACTED_DATE_BOB, VALID_CONTACTED_DESC_BOB)));
    }
}
