package seedu.address.logic.commands;


import org.junit.jupiter.api.Test;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Date;
import seedu.address.model.person.Description;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.ContactedCommand.MESSAGE_ARGUMENTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class ContactedCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        final Date date = new Date("2020-02-02");
        final Description desc = new Description("Meeting");

        assertCommandFailure(new ContactedCommand(INDEX_FIRST_PERSON,
                date, desc), model,
                String.format(MESSAGE_ARGUMENTS, INDEX_FIRST_PERSON.getOneBased(), date, desc));
    }

    @Test
    public void equals() {
        final ContactedCommand standardCommand = new ContactedCommand(INDEX_FIRST_PERSON,
                new Date(VALID_DATE_AMY), new Description(VALID_DESCRIPTION_AMY));

        // same values -> returns true
        ContactedCommand commandWithSameValues = new ContactedCommand(INDEX_FIRST_PERSON,
                new Date(VALID_DATE_AMY), new Description(VALID_DESCRIPTION_AMY));

        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new ContactedCommand(INDEX_SECOND_PERSON,
                new Date(VALID_DATE_AMY), new Description(VALID_DESCRIPTION_AMY))));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new ContactedCommand(INDEX_SECOND_PERSON,
                new Date(VALID_DATE_BOB), new Description(VALID_DESCRIPTION_BOB))));
    }
}