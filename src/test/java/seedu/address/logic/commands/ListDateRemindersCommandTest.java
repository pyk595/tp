package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
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
}
