package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListContactRemindersCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ListContactRemindersCommand object.
 */
public class ListContactRemindersCommandParser implements Parser<ListContactRemindersCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ListContactRemindersCommand
     * and returns a ListContactRemindersCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListContactRemindersCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Index index;

        try {
            index = ParserUtil.parseIndex(args);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListContactRemindersCommand.MESSAGE_USAGE), pe);
        }

        return new ListContactRemindersCommand(index);
    }
}
