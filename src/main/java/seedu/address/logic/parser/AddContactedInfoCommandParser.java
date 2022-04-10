package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACTED_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACTED_DESC;

import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddContactedInfoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contactedinfo.ContactedInfo;

/**
 * Parses input arguments and creates a new {@code AddContactedCommandParser} object
 */
public class AddContactedInfoCommandParser implements Parser<AddContactedInfoCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the {@code AddContactedCommand}
     * and returns a {@code AddContactedCommand} object for execution.
     *
     * @return a {@code AddContactedCommand} object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public AddContactedInfoCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CONTACTED_DATE, PREFIX_CONTACTED_DESC);

        if (!arePrefixesPresent(argMultimap, PREFIX_CONTACTED_DATE, PREFIX_CONTACTED_DESC)
                || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddContactedInfoCommand.MESSAGE_USAGE));
        }

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddContactedInfoCommand.MESSAGE_USAGE), ive);
        }
        ContactedInfo contactedInfo = ParserUtil.parseContactedInfo(
                argMultimap.getValue(PREFIX_CONTACTED_DATE).get(),
                argMultimap.getValue(PREFIX_CONTACTED_DESC).get());

        return new AddContactedInfoCommand(index, contactedInfo);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
