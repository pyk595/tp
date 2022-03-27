package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonOutsideDateRangePredicate;
import seedu.address.testutil.PersonBuilder;


class ContactedOutsideRangeCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_zeroDaysRange_allPersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonListSize());
        PersonOutsideDateRangePredicate predicate = new PersonOutsideDateRangePredicate(0);
        ContactedOutsideRangeCommand command = new ContactedOutsideRangeCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_zeroDaysRange_noPersonsFound() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            person = new PersonBuilder(person).addDefaultContactedInfo().build();
            ab.addPerson(person);
        }
        model = new ModelManager(ab, new UserPrefs());
        expectedModel = new ModelManager((model.getAddressBook()), new UserPrefs());
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonOutsideDateRangePredicate predicate = new PersonOutsideDateRangePredicate(0);
        ContactedOutsideRangeCommand command = new ContactedOutsideRangeCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_oneDayRange_noPersonsFound() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            person = new PersonBuilder(person).addDefaultContactedInfo().build();
            ab.addPerson(person);
        }
        model = new ModelManager(ab, new UserPrefs());
        expectedModel = new ModelManager((model.getAddressBook()), new UserPrefs());
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonOutsideDateRangePredicate predicate = new PersonOutsideDateRangePredicate(1);
        ContactedOutsideRangeCommand command = new ContactedOutsideRangeCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_noContactedInfo_onePersonFound() {
        Index indexToDel = Index.fromOneBased(1);
        model = new ModelManager(new AddressBook(), new UserPrefs());
        Person alice = new PersonBuilder(ALICE).deleteContactedInfo(indexToDel).build();
        model.addPerson(alice);
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        PersonOutsideDateRangePredicate predicate = new PersonOutsideDateRangePredicate(1);
        ContactedOutsideRangeCommand command = new ContactedOutsideRangeCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertNotEquals(Collections.emptyList(), model.getFilteredPersonList());

    }

    @Test
    public void equals() {
        PersonOutsideDateRangePredicate firstPredicate = new PersonOutsideDateRangePredicate(1);
        PersonOutsideDateRangePredicate secondPredicate = new PersonOutsideDateRangePredicate(2);
        ContactedOutsideRangeCommand firstCommand = new ContactedOutsideRangeCommand(firstPredicate);
        ContactedOutsideRangeCommand secondCommand = new ContactedOutsideRangeCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        ContactedOutsideRangeCommand firstCommandCopy = new ContactedOutsideRangeCommand(firstPredicate);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different command -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }
}
