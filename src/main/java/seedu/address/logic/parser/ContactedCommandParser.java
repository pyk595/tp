package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.ContactedCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Date;
import seedu.address.model.person.Description;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

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
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_DESCRIPTION);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedCommand.MESSAGE_USAGE), ive);
        }

        String date = argMultimap.getValue(PREFIX_DATE).orElse("");
        String description = argMultimap.getValue(PREFIX_DESCRIPTION).orElse("");

        return new ContactedCommand(index, new Date(date), new Description(description));
    }
}
