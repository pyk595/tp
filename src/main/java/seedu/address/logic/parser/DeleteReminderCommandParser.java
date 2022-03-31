package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_REMINDER;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteReminderCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteReminderCommand object.
 */
public class DeleteReminderCommandParser implements Parser<DeleteReminderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteReminderCommand
     * and returns a DeleteReminderCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteReminderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_DELETE_REMINDER);

        Index index;
        Index reminderIndex;

        if (argMultimap.getValue(PREFIX_DELETE_REMINDER).isEmpty() || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteReminderCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            reminderIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_DELETE_REMINDER).get());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteReminderCommand.MESSAGE_USAGE), pe);
        }

        return new DeleteReminderCommand(index, reminderIndex);
    }
}
