package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.tag.ReadOnlyUniqueTagList;

/**
 * Lists all unique tags in the address book to the user.
 */
public class ListTagsCommand extends Command {

    public static final String COMMAND_WORD = "tags";

    public static final String MESSAGE_SUCCESS = "Listed all tags: \n%1$s";
    public static final String MESSAGE_NO_TAGS = "You have no tags.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ReadOnlyUniqueTagList tagList = model.getUniqueTagList();

        assert model.getNumberOfUniqueTags() >= 0;
        if (model.getNumberOfUniqueTags() == 0) {
            return new CommandResult(MESSAGE_NO_TAGS);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, tagList));
    }
}
