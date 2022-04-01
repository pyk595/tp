package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
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
import seedu.address.model.reminder.ReminderList;
import seedu.address.testutil.PersonBuilder;

public class DeleteReminderCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_anyFieldsNull_throwsNullPointerException() {
        Index validReminderIndex = Index.fromOneBased(1);

        assertThrows(NullPointerException.class, () ->
                new DeleteReminderCommand(null, validReminderIndex));

        assertThrows(NullPointerException.class, () ->
                new DeleteReminderCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Index validReminderIndex = Index.fromOneBased(1);
        Reminder validReminder = new Reminder(new ReminderDescription("test"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));
        ReminderList validReminderList = new ReminderList();
        validReminderList.add(validReminder);

        AddReminderCommand addReminderCommand = new AddReminderCommand(INDEX_SECOND_PERSON, validReminder);
        assertDoesNotThrow(() -> addReminderCommand.execute(model));
        Person personToDeleteReminder = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());

        assert personToDeleteReminder.getReminderListSize() >= validReminderIndex.getOneBased();

        Person personExpected = new PersonBuilder(personToDeleteReminder).deleteReminder(validReminderIndex).build();

        DeleteReminderCommand deleteReminderCommand = new DeleteReminderCommand(INDEX_SECOND_PERSON,
                validReminderIndex);

        Model modelExpected = new ModelManager(model.getAddressBook(), new UserPrefs());
        modelExpected.setPerson(model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased()),
                personExpected);

        String messageExpected = String.format(DeleteReminderCommand.MESSAGE_DELETE_REMINDER_SUCCESS,
                validReminderIndex.getOneBased(), personExpected.getName());

        assertCommandSuccess(deleteReminderCommand, model, messageExpected, modelExpected);
    }

    @Test
    public void execute_invalidReminderIndexUnfilteredList_throwsCommandException() {
        Index invalidReminderIndex = Index.fromOneBased(1000);
        Reminder validReminder = new Reminder(new ReminderDescription("test"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));
        ReminderList validReminderList = new ReminderList();
        validReminderList.add(validReminder);

        AddReminderCommand addReminderCommand = new AddReminderCommand(INDEX_THIRD_PERSON, validReminder);
        assertDoesNotThrow(() -> addReminderCommand.execute(model));

        Person personToDeleteReminder = model.getFilteredPersonList().get(INDEX_THIRD_PERSON.getZeroBased());

        assert personToDeleteReminder.getReminderListSize() < invalidReminderIndex.getOneBased();

        DeleteReminderCommand deleteReminderCommand = new DeleteReminderCommand(INDEX_THIRD_PERSON,
                invalidReminderIndex);
        String messageExpected = String.format(DeleteReminderCommand.MESSAGE_INVALID_REMINDER_INDEX);
        assertCommandFailure(deleteReminderCommand, model, messageExpected);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index validReminderIndex = Index.fromOneBased(1);
        Index outOfBoundIndex = Index.fromOneBased(model.getAddressBook().getPersonList().size() + 10);

        assertTrue(outOfBoundIndex.getZeroBased() >= model.getAddressBook().getPersonList().size());

        DeleteReminderCommand deleteReminderCommand = new DeleteReminderCommand(outOfBoundIndex, validReminderIndex);

        assertCommandFailure(deleteReminderCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index validReminderIndex = Index.fromOneBased(1);
        Reminder validReminder = new Reminder(new ReminderDescription("test"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));
        ReminderList validReminderList = new ReminderList();
        validReminderList.add(validReminder);

        AddReminderCommand addReminderCommand = new AddReminderCommand(INDEX_FIRST_PERSON, validReminder);
        assertDoesNotThrow(() -> addReminderCommand.execute(model));

        Person personToDeleteReminder = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person personExpected = new PersonBuilder(personToDeleteReminder).deleteReminder(validReminderIndex).build();

        DeleteReminderCommand deleteReminderCommand = new DeleteReminderCommand(INDEX_FIRST_PERSON, validReminderIndex);

        Model modelExpected = new ModelManager(model.getAddressBook(), new UserPrefs());
        modelExpected.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()),
                personExpected);

        String messageExpected = String.format(DeleteReminderCommand.MESSAGE_DELETE_REMINDER_SUCCESS,
                validReminderIndex.getOneBased(), personExpected.getName());

        assertCommandSuccess(deleteReminderCommand, model, messageExpected, modelExpected);
    }

    @Test
    public void execute_outOfBoundReminderIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index invalidReminderIndex = Index.fromOneBased(1000);
        Reminder validReminder = new Reminder(new ReminderDescription("test"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));
        ReminderList validReminderList = new ReminderList();
        validReminderList.add(validReminder);

        AddReminderCommand addReminderCommand = new AddReminderCommand(INDEX_FIRST_PERSON, validReminder);
        assertDoesNotThrow(() -> addReminderCommand.execute(model));

        Person personToDeleteReminder = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        assert personToDeleteReminder.getReminderListSize() < invalidReminderIndex.getOneBased();

        DeleteReminderCommand deleteReminderCommand = new DeleteReminderCommand(INDEX_FIRST_PERSON,
                invalidReminderIndex);
        String messageExpected = String.format(DeleteReminderCommand.MESSAGE_INVALID_REMINDER_INDEX);
        assertCommandFailure(deleteReminderCommand, model, messageExpected);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        Index validReminderIndex = Index.fromOneBased(1);

        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        DeleteReminderCommand deleteReminderCommand = new DeleteReminderCommand(outOfBoundIndex, validReminderIndex);

        assertCommandFailure(deleteReminderCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Index firstIndex = Index.fromOneBased(1);
        Index secondIndex = Index.fromOneBased(2);

        DeleteReminderCommand deleteReminderCommandFirst = new DeleteReminderCommand(firstIndex, firstIndex);
        DeleteReminderCommand deleteReminderCommandSecond = new DeleteReminderCommand(secondIndex, firstIndex);
        DeleteReminderCommand deleteReminderCommandThird = new DeleteReminderCommand(firstIndex, secondIndex);
        DeleteReminderCommand deleteReminderCommandFourth = new DeleteReminderCommand(secondIndex, secondIndex);

        // compared with itself
        assertTrue(deleteReminderCommandFirst.equals(deleteReminderCommandFirst));

        // compared with a copy of itself
        DeleteReminderCommand deleteReminderCommandFirstCopy = new DeleteReminderCommand(firstIndex, firstIndex);
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
