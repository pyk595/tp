package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListContactedInfoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListContactedInfoCommandParser implements Parser<ListContactedInfoCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the ListContactRemindersCommand
     * and returns a ListContactRemindersCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListContactedInfoCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Index index;

        try {
            index = ParserUtil.parseIndex(args);
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ListContactedInfoCommand.MESSAGE_USAGE), pe);
        }

        return new ListContactedInfoCommand(index);
    }
}
