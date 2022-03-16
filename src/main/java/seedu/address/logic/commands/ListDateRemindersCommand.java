package seedu.address.logic.commands;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.date.ReminderDate;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderList;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER_DATE;

/**
 * Lists all reminders happening on a specified date.
 */
public class ListDateRemindersCommand extends Command {
    public static final String COMMAND_WORD = "reminders";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all reminders on the specified date. "
            + "Parameters: "
            + PREFIX_REMINDER_DATE + "DATE\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_REMINDER_DATE + "2022-03-15\n";

    public static final String MESSAGE_SUCCESS = "Listed all reminders on the date %1$s:\n %2$s";

    private final ReminderDate reminderDate;

    /**
     * Creates a ListDateRemindersCommand to list the reminders.
     */
    public ListDateRemindersCommand(ReminderDate reminderDate) {
        this.reminderDate = reminderDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        StringBuilder output = new StringBuilder();
        int counter = 1;
        ObservableList<Person> personList = FXCollections.observableArrayList(model.getAddressBook().getPersonList());

        for (Person person : personList) {

            ReminderList temp = person.getReminderList().sameDateAs(reminderDate);

            for (Reminder reminder : temp.getPriorityQueue()) {

                output.append(String.format("%1$d. %2$s (%3$s)\n", counter, reminder.toString(),
                        person.getName().toString()));
            }
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, reminderDate.toString(), output.toString()));
    }
}
