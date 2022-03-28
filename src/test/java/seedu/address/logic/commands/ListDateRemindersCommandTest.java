package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.date.ReminderDate;

public class ListDateRemindersCommandTest {
    private Model model;

    @BeforeEach
    public void setModel() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void constructor_nullField_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ListDateRemindersCommand(null));
    }

    @Test
    public void execute_validDate_success() {
        Model modelExpected = new ModelManager(model.getAddressBook(), new UserPrefs());

        ListDateRemindersCommand listDateRemindersCommand = new ListDateRemindersCommand(new ReminderDate(
                LocalDate.of(2022, 1, 1))); // todo
    }

    @Test
    public void equals() {
        ReminderDate reminderDateFirst = new ReminderDate(LocalDate.of(2020, 1 , 1));
        ReminderDate reminderDateSecond = new ReminderDate(LocalDate.of(2021, 1 , 1));

        ListDateRemindersCommand listContactReminderCommandFirst = new ListDateRemindersCommand(reminderDateFirst);
        ListDateRemindersCommand listContactReminderCommandSecond = new ListDateRemindersCommand(reminderDateSecond);

        // compared with itself
        assertTrue(listContactReminderCommandFirst.equals(listContactReminderCommandFirst));

        // compared with a copy of itself
        ListDateRemindersCommand listContactReminderCommandFirstCopy = new ListDateRemindersCommand(reminderDateFirst);
        assertTrue(listContactReminderCommandFirstCopy.equals(listContactReminderCommandFirst));

        // compared with a different type
        assertFalse(listContactReminderCommandFirst.equals(1));

        // compared with null
        assertFalse(listContactReminderCommandFirst.equals(null));

        // compared with same type but different values
        assertFalse(listContactReminderCommandFirst.equals(listContactReminderCommandSecond));
    }
}
