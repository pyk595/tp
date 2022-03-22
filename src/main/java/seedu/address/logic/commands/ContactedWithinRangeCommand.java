package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.PersonWithinDateRangePredicate;

public class ContactedWithinRangeCommand extends Command {

    public static final String COMMAND_WORD = "within";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts contacted within "
            + "the specified range of days and displays them as a list with index numbers.\n"
            + "Parameters: number of days\n"
            + "Example: " + COMMAND_WORD + " 5";
    private final PersonWithinDateRangePredicate predicate;

    /**
     * Constructs a {@code ContactedWithinRangeCommand} object with the given {@code PersonWithinDateRangePredicate}.
     *
     * @param predicate the predicate for the command execution in the context of {@code ContactedWithinRangeCommand}.
     */
    public ContactedWithinRangeCommand(PersonWithinDateRangePredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonListSize()));
    }

    @Override
    public boolean equals(Object other) {
        return (other == this) // short circuit if same object
                || (other instanceof ContactedWithinRangeCommand // instanceof handles nulls
                && predicate.equals(((ContactedWithinRangeCommand) other).predicate)); // state check
    }
}
