package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.AddTagCommand.MESSAGE_DUPLICATE_TAG;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FOURTH_PERSON;
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

class AddTagCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_anyFieldsNull_throwsNullPointerException() {
        Tag tag = new Tag("foo");
        assertThrows(NullPointerException.class, () -> new AddTagCommand(null, tag));
        assertThrows(NullPointerException.class, () -> new AddTagCommand(INDEX_FIRST_PERSON, null));
        assertThrows(NullPointerException.class, () -> new AddTagCommand(null, null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Tag tag = new Tag("foo");

        assert model.getFilteredPersonList().size() > 3;

        Person personToAddTag = model.getFilteredPersonList().get(INDEX_FOURTH_PERSON.getZeroBased());
        Person expectedPerson = new PersonBuilder(personToAddTag).addTags("foo").build();

        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FOURTH_PERSON, tag);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FOURTH_PERSON.getZeroBased()),
                expectedPerson);

        String expectedMessage = String.format(AddTagCommand.MESSAGE_ADD_TAG_SUCCESS, expectedPerson);

        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatedTagUnfilteredList_throwsCommandException() {
        // same tag
        Tag tag = new Tag("friends");
        Person personToAddTag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        // Alice must already have the tag "friends"
        assertTrue(personToAddTag.hasTag(tag));

        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, tag);
        String expectedMessage = String.format(MESSAGE_DUPLICATE_TAG, tag);
        assertCommandFailure(addTagCommand, model, expectedMessage);

        // case sensitivity test
        tag = new Tag("fRiEnDs");
        addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, tag);
        expectedMessage = String.format(MESSAGE_DUPLICATE_TAG, tag);
        assertCommandFailure(addTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getAddressBook().getPersonList().size() + 10);

        // ensures that outOfBoundIndex is out bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() >= model.getAddressBook().getPersonList().size());

        AddTagCommand addTagCommand = new AddTagCommand(outOfBoundIndex, new Tag("randomTag"));

        assertCommandFailure(addTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Tag tag = new Tag("foo");

        Person personToAddTag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person expectedPerson = new PersonBuilder(personToAddTag).addTags("foo").build();

        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, tag);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()),
                expectedPerson);

        String expectedMessage = String.format(AddTagCommand.MESSAGE_ADD_TAG_SUCCESS, expectedPerson);

        assertCommandSuccess(addTagCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicatedTagFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        // same tag
        Tag tag = new Tag("friends");
        Person personToAddTag = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        // Alice must already have the tag "friends"
        assertTrue(personToAddTag.hasTag(tag));

        AddTagCommand addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, tag);
        String expectedMessage = String.format(MESSAGE_DUPLICATE_TAG, tag);
        assertCommandFailure(addTagCommand, model, expectedMessage);

        // case sensitivity test
        tag = new Tag("fRiEnDs");
        addTagCommand = new AddTagCommand(INDEX_FIRST_PERSON, tag);
        expectedMessage = String.format(MESSAGE_DUPLICATE_TAG, tag);
        assertCommandFailure(addTagCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AddTagCommand addTagCommand = new AddTagCommand(outOfBoundIndex, new Tag("randomTag"));

        assertCommandFailure(addTagCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Index firstIndex = Index.fromOneBased(1);
        Index secondIndex = Index.fromOneBased(2);

        Tag firstTag = new Tag("first");
        Tag secondTag = new Tag("second");

        AddTagCommand addTagFirstCommand = new AddTagCommand(firstIndex, firstTag);
        AddTagCommand addTagSecondCommand = new AddTagCommand(secondIndex, firstTag);
        AddTagCommand addTagThirdCommand = new AddTagCommand(firstIndex, secondTag);
        AddTagCommand addTagFourthCommand = new AddTagCommand(secondIndex, secondTag);

        // same object -> returns true
        assertTrue(addTagFirstCommand.equals(addTagFirstCommand));

        // same values -> returns true
        AddTagCommand addTagFirstCommandCopy = new AddTagCommand(firstIndex, firstTag);
        assertTrue(addTagFirstCommand.equals(addTagFirstCommandCopy));

        // different types -> returns false
        assertFalse(addTagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(addTagFirstCommand.equals(null));

        // different command -> returns false
        assertFalse(addTagFirstCommand.equals(addTagSecondCommand));
        assertFalse(addTagFirstCommand.equals(addTagThirdCommand));
        assertFalse(addTagFirstCommand.equals(addTagFourthCommand));
        assertFalse(addTagSecondCommand.equals(addTagThirdCommand));
        assertFalse(addTagSecondCommand.equals(addTagFourthCommand));
        assertFalse(addTagThirdCommand.equals(addTagFourthCommand));
    }
}
