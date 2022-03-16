package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACTED_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACTED_DESC;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ContactedCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.date.RecentDate;
import seedu.address.model.person.Description;

/**
 * Parses input arguments and creates a new {@code ContactedCommand} object
 */
public class ContactedCommandParser implements Parser<ContactedCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the {@code ContactedCommand}
     * and returns a {@code ContactedCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ContactedCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CONTACTED_DATE, PREFIX_CONTACTED_DESC);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ContactedCommand.MESSAGE_USAGE), ive);
        }

        RecentDate date = ParserUtil.parseContactedDate(argMultimap.getValue(PREFIX_CONTACTED_DATE).get());
        Description description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_CONTACTED_DESC).get());

        return new ContactedCommand(index, date, description);
    }
}
