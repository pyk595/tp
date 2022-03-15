package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.date.RecentDate;
import seedu.address.model.person.Description;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class ContactedCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

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
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getAddressBook().getPersonList().size() + 10);

        // ensures that outOfBoundIndex is out bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() >= model.getAddressBook().getPersonList().size());

        ContactedCommand contactedCommand = new ContactedCommand(
                outOfBoundIndex, RecentDate.parse(VALID_CONTACTED_DATE_AMY), new Description(VALID_CONTACTED_DESC_AMY));

        assertCommandFailure(contactedCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndex_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        ContactedCommand contactedCommand = new ContactedCommand(INDEX_FIRST_PERSON,
                RecentDate.parse(VALID_CONTACTED_DATE_AMY), new Description(VALID_CONTACTED_DESC_AMY));

        Person personToAddContactedLog = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person expectedPerson = new PersonBuilder(personToAddContactedLog)
                .withDate(VALID_CONTACTED_DATE_AMY).withDescription(VALID_CONTACTED_DESC_AMY).build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()),
                expectedPerson);

        String expectedMessage = String.format(ContactedCommand.MESSAGE_ADD_CONTACTEDINFO_SUCCESS, expectedPerson);

        assertCommandSuccess(contactedCommand, model, expectedMessage, expectedModel);
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
