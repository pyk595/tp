package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contactedinfo.ContactedInfo;
import seedu.address.model.person.Person;

/**
 * Lists all reminders for a contact.
 */
public class ListContactedInfoCommand extends Command {
    public static final String COMMAND_WORD = "logs";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all contacted information for a specified contact. "
            + "Parameters: "
            + "INDEX "
            + "Example: " + COMMAND_WORD + " "
            + "3";

    public static final String MESSAGE_SUCCESS = "Listed all contacted information for %1$s:\n%2$s";

    private final Index index;

    /**
     * Creates a ListContactRemindersCommand to list the reminders.
     */
    public ListContactedInfoCommand(Index index) {
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        StringBuilder output = new StringBuilder();

        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personSpecified = model.getFilteredPersonList().get(index.getZeroBased());
        ArrayList<ContactedInfo> contactedInfoArrayList = new ArrayList<>(personSpecified.getContactedInfoList());

        for (int i = 1; i <= contactedInfoArrayList.size(); i++) {
            ContactedInfo contactedInfo = contactedInfoArrayList.get(i - 1);
            output.append(String.format("%1$d. %2$s\n", i, contactedInfo.toString()));
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, personSpecified.getName(), output.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return (other == this) // short circuit if same object
                || (other instanceof ListContactedInfoCommand) // instanceof handles nulls
                && index.equals(((ListContactedInfoCommand) other).index); // state check
    }

}
