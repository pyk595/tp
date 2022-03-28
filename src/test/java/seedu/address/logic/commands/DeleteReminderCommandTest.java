package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.date.ReminderDate;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderDescription;
import seedu.address.testutil.PersonBuilder;

public class DeleteReminderCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_anyFieldsNull_throwsNullPointerException() {
        Reminder validReminder = new Reminder(new ReminderDescription("Meeting"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));
        assertThrows(NullPointerException.class, () ->
                new DeleteReminderCommand(null, validReminder.getDescription(), validReminder.getReminderDate()));
        assertThrows(NullPointerException.class, () ->
                new DeleteReminderCommand(INDEX_FIRST_PERSON, null, validReminder.getReminderDate()));
        assertThrows(NullPointerException.class, () ->
                new DeleteReminderCommand(INDEX_FIRST_PERSON, validReminder.getDescription(), null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Reminder validReminder = new Reminder(new ReminderDescription("Meeting"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));

        Person personToDeleteReminder = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        assert personToDeleteReminder.containsReminder(validReminder);

        Person personExpected = new PersonBuilder(personToDeleteReminder).deleteReminder(validReminder).build();

        DeleteReminderCommand deleteReminderCommand = new DeleteReminderCommand(INDEX_SECOND_PERSON,
                validReminder.getDescription(), validReminder.getReminderDate());

        Model modelExpected = new ModelManager(model.getAddressBook(), new UserPrefs());
        modelExpected.setPerson(model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased()),
                personExpected);

        String messageExpected = String.format(DeleteReminderCommand.MESSAGE_DELETE_REMINDER_SUCCESS,
                validReminder);

        assertCommandSuccess(deleteReminderCommand, model, messageExpected, modelExpected);
    }

    @Test
    public void execute_missingReminderUnfilteredList_throwsCommandException() {
        Reminder invalidReminder = new Reminder(new ReminderDescription("Dinner"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));

        Person personToDeleteReminder = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());

        assertFalse(personToDeleteReminder.containsReminder(invalidReminder));

        DeleteReminderCommand deleteReminderCommand = new DeleteReminderCommand(INDEX_THIRD_PERSON,
                invalidReminder.getDescription(), invalidReminder.getReminderDate());
        String messageExpected = String.format(DeleteReminderCommand.MESSAGE_MISSING_REMINDER,
                invalidReminder.getDescription(), invalidReminder.getReminderDate());
        assertCommandFailure(deleteReminderCommand, model, messageExpected);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Reminder validReminder = new Reminder(new ReminderDescription("Meeting"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));

        Index outOfBoundIndex = Index.fromOneBased(model.getAddressBook().getPersonList().size() + 10);

        assertTrue(outOfBoundIndex.getZeroBased() >= model.getAddressBook().getPersonList().size());

        DeleteReminderCommand deleteReminderCommand = new DeleteReminderCommand(outOfBoundIndex,
                validReminder.getDescription(), validReminder.getReminderDate());

        assertCommandFailure(deleteReminderCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Reminder validReminder = new Reminder(new ReminderDescription("Meeting"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));

        Person personToDeleteReminder = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person personExpected = new PersonBuilder(personToDeleteReminder).deleteReminder(validReminder).build();

        DeleteReminderCommand deleteReminderCommand = new DeleteReminderCommand(INDEX_FIRST_PERSON,
                validReminder.getDescription(), validReminder.getReminderDate());

        Model modelExpected = new ModelManager(model.getAddressBook(), new UserPrefs());
        modelExpected.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()),
                personExpected);

        String messageExpected = String.format(DeleteReminderCommand.MESSAGE_DELETE_REMINDER_SUCCESS,
                validReminder);

        assertCommandSuccess(deleteReminderCommand, model, messageExpected, modelExpected);
    }

    @Test
    public void execute_missingReminderFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Reminder validReminder = new Reminder(new ReminderDescription("Dinner"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));

        Person personToDeleteReminder = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        assertFalse(personToDeleteReminder.containsReminder(validReminder));

        DeleteReminderCommand deleteReminderCommand = new DeleteReminderCommand(INDEX_FIRST_PERSON,
                validReminder.getDescription(), validReminder.getReminderDate());
        String messageExpected = String.format(DeleteReminderCommand.MESSAGE_MISSING_REMINDER,
                validReminder.getDescription(), validReminder.getReminderDate());
        assertCommandFailure(deleteReminderCommand, model, messageExpected);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        Reminder validReminder = new Reminder(new ReminderDescription("Meeting"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));

        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteReminderCommand deleteReminderCommand = new DeleteReminderCommand(outOfBoundIndex,
                validReminder.getDescription(), validReminder.getReminderDate());

        assertCommandFailure(deleteReminderCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Index firstIndex = Index.fromOneBased(1);
        Index secondIndex = Index.fromOneBased(2);

        Reminder firstReminder = new Reminder(new ReminderDescription("first"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));
        Reminder secondReminder = new Reminder(new ReminderDescription("second"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));

        DeleteReminderCommand deleteReminderCommandFirst = new DeleteReminderCommand(firstIndex,
                firstReminder.getDescription(), firstReminder.getReminderDate());
        DeleteReminderCommand deleteReminderCommandSecond = new DeleteReminderCommand(secondIndex,
                firstReminder.getDescription(), firstReminder.getReminderDate());
        DeleteReminderCommand deleteReminderCommandThird = new DeleteReminderCommand(firstIndex,
                secondReminder.getDescription(), secondReminder.getReminderDate());
        DeleteReminderCommand deleteReminderCommandFourth = new DeleteReminderCommand(secondIndex,
                secondReminder.getDescription(), secondReminder.getReminderDate());

        // compared with itself
        assertTrue(deleteReminderCommandFirst.equals(deleteReminderCommandFirst));

        // compared with a copy of itself
        DeleteReminderCommand deleteReminderCommandFirstCopy = new DeleteReminderCommand(firstIndex,
                firstReminder.getDescription(), firstReminder.getReminderDate());
        assertTrue(deleteReminderCommandFirstCopy.equals(deleteReminderCommandFirst));

        // compared with a different type
        assertFalse(deleteReminderCommandFirst.equals(1));

        // compared with null
        assertFalse(deleteReminderCommandFirst.equals(null));

        // compared with same type but different values
        assertFalse(deleteReminderCommandFirst.equals(deleteReminderCommandSecond));
        assertFalse(deleteReminderCommandFirst.equals(deleteReminderCommandThird));
        assertFalse(deleteReminderCommandFirst.equals(deleteReminderCommandFourth));
        assertFalse(deleteReminderCommandSecond.equals(deleteReminderCommandThird));
        assertFalse(deleteReminderCommandSecond.equals(deleteReminderCommandFourth));
        assertFalse(deleteReminderCommandThird.equals(deleteReminderCommandFourth));
    }
}
