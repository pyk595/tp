package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.ReminderList;

/**
 * Lists all reminders for a contact.
 */
public class ListContactRemindersCommand extends Command {

    public static final String COMMAND_WORD = "reminder";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all reminders for a specified contact.\n\n"
            + "Parameters: "
            + "INDEX\n\n"
            + "Example: " + COMMAND_WORD + " "
            + "3";

    public static final String MESSAGE_SUCCESS = "Listed all reminders for %1$s:\n%2$s";

    private final Index index;

    /**
     * Creates a ListContactRemindersCommand to list the reminders.
     */
    public ListContactRemindersCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personSpecified = model.getFilteredPerson(index);
        ReminderList reminderList = personSpecified.getReminderList();

        return new CommandResult(String.format(MESSAGE_SUCCESS, personSpecified.getName(), reminderList));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof ListContactRemindersCommand)) {
            return false;
        }

        ListContactRemindersCommand listContactRemindersCommand = (ListContactRemindersCommand) other;
        return this.index.equals(listContactRemindersCommand.index);
    }
}
