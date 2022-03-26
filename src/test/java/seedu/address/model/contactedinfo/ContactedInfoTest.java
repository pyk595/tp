package seedu.address.model.contactedinfo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DESC_BOB;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.date.RecentDate;
import seedu.address.model.description.Description;

public class ContactedInfoTest {
    @Test
    public void constructor_null_throwsNullPointerException() {

        RecentDate recentDate = RecentDate.parse("2020-02-02");
        Description description = new Description("meet up");
        assertThrows(NullPointerException.class, () -> new ContactedInfo(null, description));
        assertThrows(NullPointerException.class, () -> new ContactedInfo(recentDate, null));
        assertThrows(NullPointerException.class, () -> new ContactedInfo(null, null));
    }

    @Test
    public void equals() {
        ContactedInfo first = new ContactedInfo(RecentDate.parse("2020-01-02"), new Description("phone call"));
        ContactedInfo second = new ContactedInfo(RecentDate.parse("2020-01-01"), new Description("meeting"));

        // same values -> returns true
        assertTrue(
                new ContactedInfo(RecentDate.parse(VALID_CONTACTED_DATE_AMY),
                new Description(VALID_CONTACTED_DESC_AMY))
                .equals(
                new ContactedInfo(RecentDate.parse(VALID_CONTACTED_DATE_AMY),
                new Description(VALID_CONTACTED_DESC_AMY))));

        // same object -> returns true
        assertTrue(first.equals(first));

        // null -> returns false
        assertFalse(new ContactedInfo(RecentDate.parse(VALID_CONTACTED_DATE_AMY),
                new Description(VALID_CONTACTED_DESC_AMY)).equals(null));

        // different type -> returns false
        assertFalse(first.equals(5));

        // different person -> returns false
        assertFalse(first.equals(second));

        // not equal
        assertFalse(
                new ContactedInfo(RecentDate.parse(VALID_CONTACTED_DATE_AMY),
                new Description(VALID_CONTACTED_DESC_AMY))
                .equals(
                new ContactedInfo(RecentDate.parse(VALID_CONTACTED_DATE_BOB),
                new Description(VALID_CONTACTED_DESC_BOB))));
    }
}
