package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonWithinDateRangePredicate;
import seedu.address.testutil.PersonBuilder;

public class ContactedWithinRangeCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

    @Test
    public void execute_zeroDaysRange_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonWithinDateRangePredicate predicate = new PersonWithinDateRangePredicate(0);
        ContactedWithinRangeCommand command = new ContactedWithinRangeCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_zeroDaysRange_onePersonFound() {
        Person personToAddContactedLog = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person expectedPerson = new PersonBuilder(personToAddContactedLog)
                .addDefaultContactedInfo().build();
        model.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()),
                expectedPerson);
        ModelManager newExpectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonWithinDateRangePredicate predicate = new PersonWithinDateRangePredicate(0);
        ContactedWithinRangeCommand command = new ContactedWithinRangeCommand(predicate);
        newExpectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, newExpectedModel);
        assertEquals(model.getFilteredPersonList(), newExpectedModel.getFilteredPersonList());
    }

    @Test
    public void execute_zeroDaysRange_multiplePersonsFound() {
        Person personToAddContactedLog = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person expectedPerson = new PersonBuilder(personToAddContactedLog)
                .addDefaultContactedInfo().build();
        model.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()),
                expectedPerson);
        Person secondPersonToAddContactedLog = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person expectedSecondPerson = new PersonBuilder(secondPersonToAddContactedLog)
                .addDefaultContactedInfo().build();
        model.setPerson(model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased()),
                expectedSecondPerson);
        ModelManager newExpectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PersonWithinDateRangePredicate predicate = new PersonWithinDateRangePredicate(0);
        ContactedWithinRangeCommand command = new ContactedWithinRangeCommand(predicate);
        newExpectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, newExpectedModel);
        assertEquals(model.getFilteredPersonList(), newExpectedModel.getFilteredPersonList());
    }

    @Test
    public void equals() {
        PersonWithinDateRangePredicate firstPredicate = new PersonWithinDateRangePredicate(1);
        PersonWithinDateRangePredicate secondPredicate = new PersonWithinDateRangePredicate(2);
        ContactedWithinRangeCommand firstCommand = new ContactedWithinRangeCommand(firstPredicate);
        ContactedWithinRangeCommand secondCommand = new ContactedWithinRangeCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        ContactedWithinRangeCommand firstCommandCopy = new ContactedWithinRangeCommand(firstPredicate);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different command -> returns false
        assertFalse(firstCommand.equals(secondCommand));

    }

}
