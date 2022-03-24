package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderList;

/**
 * Lists all reminders for a contact.
 */
public class ListContactRemindersCommand extends Command {

    public static final String COMMAND_WORD = "reminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all reminders for a specified contact. "
            + "Parameters: "
            + "INDEX\n"
            + "Example: " + COMMAND_WORD + " "
            + "3\n";

    public static final String MESSAGE_SUCCESS = "Listed all reminders for %1$s:\n%2$s";

    private final Index index;

    /**
     * Creates a ListContactRemindersCommand to list the reminders.
     */
    public ListContactRemindersCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        StringBuilder output = new StringBuilder();
        int counter = 1;
        Person personSpecified = model.getFilteredPersonList().get(index.getZeroBased());
        ReminderList reminderList = personSpecified.getReminderList();

        for (Reminder reminder : reminderList.getPriorityQueue()) {
            output.append(String.format("%1$d. %2$s\n", counter, reminder.toString()));
            counter++;
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, personSpecified.getName(), output.toString()));
    }
}
