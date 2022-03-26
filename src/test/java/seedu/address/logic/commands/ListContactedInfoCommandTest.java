package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;


public class ListContactedInfoCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validIndex_showsSameLogs() {
        Index index = Index.fromOneBased(1);
        Person expectedPerson = model.getFilteredPerson(index);
        String expectedMessage = String.format(ListContactedInfoCommand.MESSAGE_LIST_CONTACTED_INFO_SUCCESS,
                expectedPerson.getName(), expectedPerson.getContactedInfoListToString());
        assertCommandSuccess(new ListContactedInfoCommand(index), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validBoundaryIndex_showsSameLogs() {
        Index index = Index.fromOneBased(getTypicalPersons().size());
        Person expectedPerson = model.getFilteredPerson(index);
        String expectedMessage = String.format(ListContactedInfoCommand.MESSAGE_LIST_CONTACTED_INFO_SUCCESS,
                expectedPerson.getName(), expectedPerson.getContactedInfoListToString());
        assertCommandSuccess(new ListContactedInfoCommand(index), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validBoundaryIndexLessOne_showsSameLogs() {
        Index index = Index.fromOneBased(getTypicalPersons().size() - 1);
        Person expectedPerson = model.getFilteredPerson(index);
        String expectedMessage = String.format(ListContactedInfoCommand.MESSAGE_LIST_CONTACTED_INFO_SUCCESS,
                expectedPerson.getName(), expectedPerson.getContactedInfoListToString());
        assertCommandSuccess(new ListContactedInfoCommand(index), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validBoundaryIndexMoreOne_failure() {
        Index index = Index.fromOneBased(getTypicalPersons().size() + 1);
        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        assertCommandFailure(new ListContactedInfoCommand(index), model, expectedMessage);
    }

    @Test
    public void equals() {
        Index first = Index.fromOneBased(1);
        Index second = Index.fromOneBased(2);

        ListContactedInfoCommand firstCommand = new ListContactedInfoCommand(first);
        ListContactedInfoCommand secondCommand = new ListContactedInfoCommand(second);


        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        ListContactedInfoCommand firstCommandCopy = new ListContactedInfoCommand(first);
        assertTrue(firstCommandCopy.equals(firstCommand));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different command -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }
}
