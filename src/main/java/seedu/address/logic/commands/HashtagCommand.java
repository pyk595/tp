package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.person.PersonWithTagPredicate;

/**
 * Finds and lists all persons in address book who is tagged to the given tag.
 * Keyword matching is case-insensitive.
 */
public class HashtagCommand extends Command {

    public static final String COMMAND_WORD = "#";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all contacts that is tagged to "
            + "the specified tag (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: TAG\n"
            + "Example: " + COMMAND_WORD + "client";

    private final PersonWithTagPredicate predicate;

    /**
     * Constructs a {@code HashtagCommand} object with the given {@code PersonWithTagPredicate}.
     *
     * @param predicate the predicate for the command execution in the context of {@code HashtagCommand}.
     */
    public HashtagCommand(PersonWithTagPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    /**
     * Returns true if the given input string is a hashtag (any string that starts with #).
     *
     * @param input the input string to verify.
     * @return true if the given input string is a hashtag (any string that starts with #).
     */
    public static boolean isHashtagCommand(String input) {
        if (input == null) {
            return false;
        }
        String trimmedInput = input.trim();
        return trimmedInput.startsWith(COMMAND_WORD);
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return (other == this) // short circuit if same object
                || (other instanceof HashtagCommand // instanceof handles nulls
                && predicate.equals(((HashtagCommand) other).predicate)); // state check
    }
}
