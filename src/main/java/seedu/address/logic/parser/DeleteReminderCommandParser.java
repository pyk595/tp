package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER_DESCRIPTION;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteReminderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.date.ReminderDate;
import seedu.address.model.reminder.ReminderDescription;

/**
 * Parses input arguments and creates a new DeleteReminderCommand object.
 */
public class DeleteReminderCommandParser implements Parser<DeleteReminderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteReminderCommand
     * and returns a DeleteReminderCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteReminderCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_REMINDER_DESCRIPTION,
                        PREFIX_REMINDER_DATE);

        Index index;
        ReminderDescription reminderDescription;
        ReminderDate reminderDate;

        if (!arePrefixesPresent(argMultimap, PREFIX_REMINDER_DESCRIPTION, PREFIX_REMINDER_DATE)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteReminderCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
            reminderDescription = ParserUtil.parseReminderDescription(
                    argMultimap.getValue(PREFIX_REMINDER_DESCRIPTION).get());
            reminderDate = ParserUtil.parseReminderDate(
                    argMultimap.getValue(PREFIX_REMINDER_DATE).get());
        } catch (seedu.address.logic.parser.exceptions.ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    DeleteReminderCommand.MESSAGE_USAGE), pe);
        }

        return new DeleteReminderCommand(index, reminderDescription, reminderDate);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
