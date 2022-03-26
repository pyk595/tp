package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.DeleteTagCommand.MESSAGE_MISSING_TAG;
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
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class DeleteTagCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_anyFieldsNull_throwsNullPointerException() {
        Tag tag = new Tag("foo");
        assertThrows(NullPointerException.class, () -> new DeleteTagCommand(null, tag));
        assertThrows(NullPointerException.class, () -> new DeleteTagCommand(INDEX_FIRST_PERSON, null));
        assertThrows(NullPointerException.class, () -> new DeleteTagCommand(null, null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Tag tag = new Tag("friends");

        Person personToDeleteTag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        assert personToDeleteTag.hasTag(tag);

        Person expectedPerson = new PersonBuilder(personToDeleteTag).deleteTags("friends").build();

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, tag);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()),
                expectedPerson);

        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_DELETE_TAG_SUCCESS, expectedPerson);

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_missingTagUnfilteredList_throwsCommandException() {
        // same tag
        Tag tag = new Tag("randomTag");
        Person personToDeleteTag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        // Alice must not have the tag "randomTag"
        assertFalse(personToDeleteTag.hasTag(tag));

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, tag);
        String expectedMessage = String.format(MESSAGE_MISSING_TAG, tag);
        assertCommandFailure(deleteTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getAddressBook().getPersonList().size() + 10);

        // ensures that outOfBoundIndex is out bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() >= model.getAddressBook().getPersonList().size());

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(outOfBoundIndex, new Tag("randomTag"));

        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Tag tag = new Tag("friends");

        Person personToDeleteTag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person expectedPerson = new PersonBuilder(personToDeleteTag).deleteTags("friends").build();

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, tag);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()),
                expectedPerson);

        String expectedMessage = String.format(DeleteTagCommand.MESSAGE_DELETE_TAG_SUCCESS, expectedPerson);

        assertCommandSuccess(deleteTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_missingTagFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // same tag
        Tag tag = new Tag("randomTag");
        Person personToDeleteTag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        // Alice must not have the tag "randomTag"
        assertFalse(personToDeleteTag.hasTag(tag));

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, tag);
        String expectedMessage = String.format(MESSAGE_MISSING_TAG, tag);
        assertCommandFailure(deleteTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteTagCommand deleteTagCommand = new DeleteTagCommand(outOfBoundIndex, new Tag("randomTag"));

        assertCommandFailure(deleteTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Index firstIndex = Index.fromOneBased(1);
        Index secondIndex = Index.fromOneBased(2);

        Tag firstTag = new Tag("first");
        Tag secondTag = new Tag("second");

        DeleteTagCommand deleteTagFirstCommand = new DeleteTagCommand(firstIndex, firstTag);
        DeleteTagCommand deleteTagSecondCommand = new DeleteTagCommand(secondIndex, firstTag);
        DeleteTagCommand deleteTagThirdCommand = new DeleteTagCommand(firstIndex, secondTag);
        DeleteTagCommand deleteTagFourthCommand = new DeleteTagCommand(secondIndex, secondTag);

        // same object -> returns true
        assertTrue(deleteTagFirstCommand.equals(deleteTagFirstCommand));

        // same values -> returns true
        DeleteTagCommand deleteTagFirstCommandCopy = new DeleteTagCommand(firstIndex, firstTag);
        assertTrue(deleteTagFirstCommand.equals(deleteTagFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteTagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteTagFirstCommand.equals(null));

        // different command -> returns false
        assertFalse(deleteTagFirstCommand.equals(deleteTagSecondCommand));
        assertFalse(deleteTagFirstCommand.equals(deleteTagThirdCommand));
        assertFalse(deleteTagFirstCommand.equals(deleteTagFourthCommand));
        assertFalse(deleteTagSecondCommand.equals(deleteTagThirdCommand));
        assertFalse(deleteTagSecondCommand.equals(deleteTagFourthCommand));
        assertFalse(deleteTagThirdCommand.equals(deleteTagFourthCommand));
    }
}
