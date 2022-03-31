package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.model.reminder.ReminderList.MESSAGE_DUPLICATE_REMINDER;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.date.ReminderDate;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderDescription;
import seedu.address.testutil.PersonBuilder;

public class AddReminderCommandTest {

    private Model model;

    @BeforeEach
    public void setModel() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void constructor_anyFieldsNull_throwsNullPointerException() {
        Reminder validReminder = new Reminder(new ReminderDescription("meeting"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));
        assertThrows(NullPointerException.class, () -> new AddReminderCommand(null, validReminder));
        assertThrows(NullPointerException.class, () -> new AddReminderCommand(INDEX_FIRST_PERSON, null));
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Reminder validReminder = new Reminder(new ReminderDescription("dinner"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));

        Person personToAddReminder = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person personExpected = new PersonBuilder(personToAddReminder).addReminder(validReminder).build();

        AddReminderCommand addReminderCommand = new AddReminderCommand(INDEX_FIRST_PERSON, validReminder);

        Model modelExpected = new ModelManager(model.getAddressBook(), new UserPrefs());
        modelExpected.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()),
                personExpected);

        String messageExpected = String.format(AddReminderCommand.MESSAGE_ADD_REMINDER_SUCCESS,
                validReminder, personExpected.getName());

        assertCommandSuccess(addReminderCommand, model, messageExpected, modelExpected);
    }

    @Test
    public void execute_duplicateReminderUnfilteredList_throwsCommandException() throws CommandException {
        Reminder validReminder = new Reminder(new ReminderDescription("dinner"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));

        assert model.getFilteredPersonList().size() > 1;

        Person personToAddReminder = model.getFilteredPersonList().get(INDEX_SECOND_PERSON.getZeroBased());
        Person personExpected = new PersonBuilder(personToAddReminder).addReminder(validReminder).build();

        assertTrue(personExpected.containsReminder(validReminder));

        model.setPerson(personToAddReminder, personExpected);

        AddReminderCommand addReminderCommand = new AddReminderCommand(INDEX_SECOND_PERSON, validReminder);
        String messageExpected = String.format(MESSAGE_DUPLICATE_REMINDER,
                validReminder, personToAddReminder);
        assertCommandFailure(addReminderCommand, model, messageExpected);

        validReminder = new Reminder(new ReminderDescription("DiNnEr"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));
        addReminderCommand = new AddReminderCommand(INDEX_SECOND_PERSON, validReminder);
        messageExpected = String.format(MESSAGE_DUPLICATE_REMINDER,
                validReminder, personToAddReminder);
        assertCommandFailure(addReminderCommand, model, messageExpected);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Reminder validReminder = new Reminder(new ReminderDescription("dinner"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));

        Index outOfBoundIndex = Index.fromOneBased(model.getAddressBook().getPersonList().size() + 10);

        assertTrue(outOfBoundIndex.getZeroBased() >= model.getAddressBook().getPersonList().size());

        AddReminderCommand addReminderCommand = new AddReminderCommand(outOfBoundIndex, validReminder);

        assertCommandFailure(addReminderCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() throws CommandException {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Reminder validReminder = new Reminder(new ReminderDescription("dinner"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));

        Person personToAddReminder = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person personExpected = new PersonBuilder(personToAddReminder).addReminder(validReminder).build();

        AddReminderCommand addReminderCommand = new AddReminderCommand(INDEX_FIRST_PERSON, validReminder);

        Model modelExpected = new ModelManager(model.getAddressBook(), new UserPrefs());
        modelExpected.setPerson(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()),
                personExpected);

        String messageExpected = String.format(AddReminderCommand.MESSAGE_ADD_REMINDER_SUCCESS,
                validReminder, personExpected.getName());

        assertCommandSuccess(addReminderCommand, model, messageExpected, modelExpected);
    }

    @Test
    public void execute_duplicateReminderFilteredList_throwsCommandException() throws CommandException {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Reminder validReminder = new Reminder(new ReminderDescription("dinner"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));

        Person personToAddReminder = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person personExpected = new PersonBuilder(personToAddReminder).addReminder(validReminder).build();

        assertTrue(personExpected.containsReminder(validReminder));

        model.setPerson(personToAddReminder, personExpected);

        AddReminderCommand addReminderCommand = new AddReminderCommand(INDEX_FIRST_PERSON, validReminder);
        String messageExpected = String.format(MESSAGE_DUPLICATE_REMINDER,
                validReminder, personToAddReminder);
        assertCommandFailure(addReminderCommand, model, messageExpected);

        validReminder = new Reminder(new ReminderDescription("DiNnEr"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));
        addReminderCommand = new AddReminderCommand(INDEX_FIRST_PERSON, validReminder);
        messageExpected = String.format(MESSAGE_DUPLICATE_REMINDER,
                validReminder, personToAddReminder);
        assertCommandFailure(addReminderCommand, model, messageExpected);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        Reminder validReminder = new Reminder(new ReminderDescription("dinner"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));

        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        AddReminderCommand addReminderCommand = new AddReminderCommand(outOfBoundIndex, validReminder);

        assertCommandFailure(addReminderCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Index firstIndex = Index.fromOneBased(1);
        Index secondIndex = Index.fromOneBased(2);

        Reminder firstReminder = new Reminder(new ReminderDescription("first"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));
        Reminder secondReminder = new Reminder(new ReminderDescription("second"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));

        AddReminderCommand addReminderCommandFirst = new AddReminderCommand(firstIndex, firstReminder);
        AddReminderCommand addReminderCommandSecond = new AddReminderCommand(secondIndex, firstReminder);
        AddReminderCommand addReminderCommandThird = new AddReminderCommand(firstIndex, secondReminder);
        AddReminderCommand addReminderCommandFourth = new AddReminderCommand(secondIndex, secondReminder);

        // compared with itself
        assertTrue(addReminderCommandFirst.equals(addReminderCommandFirst));

        // compared with a copy of itself
        AddReminderCommand addReminderCommandFirstCopy = new AddReminderCommand(firstIndex, firstReminder);
        assertTrue(addReminderCommandFirstCopy.equals(addReminderCommandFirst));

        // compared with a different type
        assertFalse(addReminderCommandFirst.equals(1));

        // compared with null
        assertFalse(addReminderCommandFirst.equals(null));

        // compared with same type but different values
        assertFalse(addReminderCommandFirst.equals(addReminderCommandSecond));
        assertFalse(addReminderCommandFirst.equals(addReminderCommandThird));
        assertFalse(addReminderCommandFirst.equals(addReminderCommandFourth));
        assertFalse(addReminderCommandSecond.equals(addReminderCommandThird));
        assertFalse(addReminderCommandSecond.equals(addReminderCommandFourth));
        assertFalse(addReminderCommandThird.equals(addReminderCommandFourth));
    }
}
