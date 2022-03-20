package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DATE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DATE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACTED_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
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
import seedu.address.model.contactedinfo.ContactedInfo;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class AddContactedInfoCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_anyFieldsNull_throwsNullPointerException() {

        ContactedInfo contactedInfo = ContactedInfo.getDefaultContactedInfo();
        assertThrows(NullPointerException.class, () -> new AddContactedInfoCommand(null, contactedInfo));
        assertThrows(NullPointerException.class, (
                ) -> new AddContactedInfoCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getAddressBook().getPersonList().size() + 10);

        // ensures that outOfBoundIndex is out bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() >= model.getAddressBook().getPersonList().size());

        AddContactedInfoCommand contactedCommand = new AddContactedInfoCommand(
                outOfBoundIndex, new ContactedInfo(VALID_CONTACTED_DATE_AMY, VALID_CONTACTED_DESC_AMY));

        assertCommandFailure(contactedCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndex_success() {
        ContactedInfo contactedInfo = new ContactedInfo(VALID_CONTACTED_DATE_AMY, VALID_CONTACTED_DESC_AMY);

        Person personToAddContactedLog = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person expectedPerson = new PersonBuilder(personToAddContactedLog)
                .addContactedInfo(VALID_CONTACTED_DATE_AMY + " " + VALID_CONTACTED_DESC_AMY).build();

        AddContactedInfoCommand contactedCommand = new AddContactedInfoCommand(INDEX_FIRST_PERSON,
                contactedInfo);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()),
                expectedPerson);

        String expectedMessage = String.format(
                AddContactedInfoCommand.MESSAGE_ADD_CONTACTEDINFO_SUCCESS, expectedPerson);

        assertCommandSuccess(contactedCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final AddContactedInfoCommand standardCommand = new AddContactedInfoCommand(INDEX_FIRST_PERSON,
                new ContactedInfo(VALID_CONTACTED_DATE_AMY, VALID_CONTACTED_DESC_AMY));

        // same values -> returns true
        AddContactedInfoCommand commandWithSameValues = new AddContactedInfoCommand(INDEX_FIRST_PERSON,
                new ContactedInfo(VALID_CONTACTED_DATE_AMY, VALID_CONTACTED_DESC_AMY));

        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new AddContactedInfoCommand(INDEX_SECOND_PERSON,
                new ContactedInfo(VALID_CONTACTED_DATE_AMY, VALID_CONTACTED_DESC_AMY))));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new AddContactedInfoCommand(INDEX_SECOND_PERSON,
                new ContactedInfo(VALID_CONTACTED_DATE_BOB, VALID_CONTACTED_DESC_BOB))));
    }
}
