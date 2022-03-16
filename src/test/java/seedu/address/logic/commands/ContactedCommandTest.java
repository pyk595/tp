package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DESC_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.model.date.RecentDate;
import seedu.address.model.person.Description;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class ContactedCommandTest {

    @Test
    public void constructor_anyFieldsNull_throwsNullPointerException() {

        RecentDate recentDate = RecentDate.defaultRecentDate();
        Description description = Description.defaultDesc();
        assertThrows(NullPointerException.class, () -> new ContactedCommand(null, recentDate, description));
        assertThrows(NullPointerException.class, (
                    ) -> new ContactedCommand(INDEX_FIRST_PERSON, null, description));
        assertThrows(NullPointerException.class, (
                    ) -> new ContactedCommand(INDEX_FIRST_PERSON, null, description));
    }

    @Test
    public void equals() {
        final ContactedCommand standardCommand = new ContactedCommand(INDEX_FIRST_PERSON,
                RecentDate.parse(VALID_CONTACTED_DATE_AMY), new Description(VALID_CONTACTED_DESC_AMY));

        // same values -> returns true
        ContactedCommand commandWithSameValues = new ContactedCommand(INDEX_FIRST_PERSON,
                RecentDate.parse(VALID_CONTACTED_DATE_AMY), new Description(VALID_CONTACTED_DESC_AMY));

        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new ContactedCommand(INDEX_SECOND_PERSON,
                RecentDate.parse(VALID_CONTACTED_DATE_AMY), new Description(VALID_CONTACTED_DESC_AMY))));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new ContactedCommand(INDEX_SECOND_PERSON,
                RecentDate.parse(VALID_CONTACTED_DATE_BOB), new Description(VALID_CONTACTED_DESC_BOB))));
    }
}
