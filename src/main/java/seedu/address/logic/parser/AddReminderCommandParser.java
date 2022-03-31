package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER_DESCRIPTION;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddReminderCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.date.ReminderDate;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderDescription;

/**
 * Parses input arguments and creates a new AddReminderCommand object.
 */
public class AddReminderCommandParser implements Parser<AddReminderCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddReminderCommand
     * and returns a AddReminderCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddReminderCommand parse(String args) throws ParseException {
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
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddReminderCommand.MESSAGE_USAGE));
        }

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddReminderCommand.MESSAGE_USAGE), pe);
        }

        reminderDescription = ParserUtil.parseReminderDescription(
                argMultimap.getValue(PREFIX_REMINDER_DESCRIPTION).get());
        reminderDate = ParserUtil.parseReminderDate(argMultimap.getValue(PREFIX_REMINDER_DATE).get());

        Reminder toAdd = new Reminder(reminderDescription, reminderDate);

        return new AddReminderCommand(index, toAdd);
    }

    /**
     * Checks if the {@code Prefix} are present
     *
     * @return true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
