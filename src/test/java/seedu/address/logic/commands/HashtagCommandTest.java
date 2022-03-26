package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PersonWithTagPredicate;
import seedu.address.model.tag.Tag;

class HashtagCommandTest {

    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_noMatchingTag_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonWithTagPredicate predicate = new PersonWithTagPredicate(new Tag("randomTag"));
        HashtagCommand command = new HashtagCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_matchingTag_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonWithTagPredicate predicate = new PersonWithTagPredicate(new Tag("friends"));
        HashtagCommand command = new HashtagCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void equals() {
        PersonWithTagPredicate firstPredicate =
                new PersonWithTagPredicate(new Tag("first"));
        PersonWithTagPredicate secondPredicate =
                new PersonWithTagPredicate(new Tag("second"));

        HashtagCommand hashtagFirstCommand = new HashtagCommand(firstPredicate);
        HashtagCommand hashtagSecondCommand = new HashtagCommand(secondPredicate);

        // same object -> returns true
        assertTrue(hashtagFirstCommand.equals(hashtagFirstCommand));

        // same values -> returns true
        HashtagCommand hashtagFirstCommandCopy = new HashtagCommand(firstPredicate);
        assertTrue(hashtagFirstCommand.equals(hashtagFirstCommandCopy));

        // different types -> returns false
        assertFalse(hashtagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(hashtagFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(hashtagFirstCommand.equals(hashtagSecondCommand));
    }
}
