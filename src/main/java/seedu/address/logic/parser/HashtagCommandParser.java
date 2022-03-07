package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.HashtagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonWithTagPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new HashtagCommand object
 */
public class HashtagCommandParser implements Parser<HashtagCommand> {

    /**
     * Parses the given tag of {@code String} type in the context of the HashtagCommand
     * and returns a HashtagCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format,
     *                        either the given tag is empty or invalid (not alphanumerical).
     */
    @Override
    public HashtagCommand parse(String input) throws ParseException {
        String trimmedInput = input.trim();
        if (trimmedInput.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, HashtagCommand.MESSAGE_USAGE));
        }
        Tag newTag = ParserUtil.parseTag(trimmedInput);
        return new HashtagCommand(new PersonWithTagPredicate(newTag));
    }
}
