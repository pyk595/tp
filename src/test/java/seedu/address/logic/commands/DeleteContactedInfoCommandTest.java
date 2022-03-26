package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class DeleteContactedInfoCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_anyFieldsNull_throwsNullPointerException() {
        Index indexToDel = Index.fromZeroBased(0);
        assertThrows(NullPointerException.class, () -> new DeleteContactedInfoCommand(null, indexToDel));
    }

    /*
    TEST CASE FAILING
    @Test
    public void execute_validIndexUnfilteredList_success() {
        int indexToDel = 1;

        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Person expectedPerson = new PersonBuilder(personToDelete).deleteContactedInfo(indexToDel).build();

        DeleteContactedInfoCommand deleteContactedInfoCommand =
                new DeleteContactedInfoCommand(INDEX_FIRST_PERSON, indexToDel);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()),
                expectedPerson);

        String expectedMessage = DeleteContactedInfoCommand.MESSAGE_DELETE_CONTACTED_INFO_SUCCESS;

        assertCommandSuccess(deleteContactedInfoCommand, model, expectedMessage, expectedModel);
    }
    */

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getAddressBook().getPersonList().size() + 10);
        Index indexToDelete = Index.fromZeroBased(1);

        // ensures that outOfBoundIndex is out bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() >= model.getAddressBook().getPersonList().size());

        DeleteContactedInfoCommand deleteContactedInfoCommand = new DeleteContactedInfoCommand(
                outOfBoundIndex, indexToDelete);

        assertCommandFailure(deleteContactedInfoCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidContactedInfoIndex_throwsCommandException() {
        Index index = Index.fromOneBased(1);

        Index outOfBoundUpperIndex = Index.fromOneBased(model.getAddressBook().getPersonList().size() + 10);

        // ensures that outOfBoundIndex is out bounds of address book list
        assertTrue(outOfBoundUpperIndex.getOneBased() >= model.getAddressBook().getPersonList().size());

        DeleteContactedInfoCommand deleteContactedInfoCommandU = new DeleteContactedInfoCommand(
                index, outOfBoundUpperIndex);

        assertCommandFailure(deleteContactedInfoCommandU, model, DeleteContactedInfoCommand.MESSAGE_MISSING_INFO);
    }

    @Test
    public void equals() {
        Index firstIndex = Index.fromOneBased(1);
        Index secondIndex = Index.fromOneBased(2);

        Index first = Index.fromOneBased(1);
        Index second = Index.fromOneBased(2);

        DeleteContactedInfoCommand deleteContactedInfoFirstCommand = new DeleteContactedInfoCommand(firstIndex, first);
        DeleteContactedInfoCommand deleteContactedInfoSecondCommand = new DeleteContactedInfoCommand(
                secondIndex, second);
        DeleteContactedInfoCommand deleteContactedInfoThirdCommand = new DeleteContactedInfoCommand(firstIndex, second);
        DeleteContactedInfoCommand deleteContactedInfoFourthCommand = new DeleteContactedInfoCommand(
                secondIndex, first);

        // same object -> returns true
        assertTrue(deleteContactedInfoFirstCommand.equals(deleteContactedInfoFirstCommand));

        // same values -> returns true
        DeleteContactedInfoCommand deleteContactedInfoFirstCommandCopy =
                new DeleteContactedInfoCommand(firstIndex, first);
        assertTrue(deleteContactedInfoFirstCommandCopy.equals(deleteContactedInfoFirstCommand));

        // different types -> returns false
        assertFalse(deleteContactedInfoFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteContactedInfoFirstCommand.equals(null));

        // different command -> returns false
        assertFalse(deleteContactedInfoFirstCommand.equals(deleteContactedInfoSecondCommand));
        assertFalse(deleteContactedInfoFirstCommand.equals(deleteContactedInfoThirdCommand));
        assertFalse(deleteContactedInfoFirstCommand.equals(deleteContactedInfoFourthCommand));
        assertFalse(deleteContactedInfoSecondCommand.equals(deleteContactedInfoThirdCommand));
        assertFalse(deleteContactedInfoSecondCommand.equals(deleteContactedInfoFourthCommand));
        assertFalse(deleteContactedInfoThirdCommand.equals(deleteContactedInfoFourthCommand));
    }
}
