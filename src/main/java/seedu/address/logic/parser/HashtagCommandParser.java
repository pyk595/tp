package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.logic.commands.HashtagCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonWithTagPredicate;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new {@code HashtagCommand} object.
 */
public class HashtagCommandParser implements Parser<HashtagCommand> {

    public static final Prefix PREFIX_HASHTAG = new Prefix(HashtagCommand.COMMAND_WORD);
    public static final String MESSAGE_CONSTRAINT = String.format("input should start with %1$s",
            HashtagCommand.COMMAND_WORD);

    /**
     * Parses the given tag of {@code String} type in the context of the {@code HashtagCommand}
     * and returns a {@code HashtagCommand} object for execution. {@code isHashtagCommand(String) } must be called and
     * checked before this method call.
     *
     * @param input the hashtag command to be parsed.
     * @return a new {@code HashtagCommand} object
     * @throws ParseException if the user input does not conform the expected format,
     *                        either the given tag is empty or invalid (not alphanumerical).
     * @see HashtagCommand
     * @see #isHashtagCommand(String)
     */
    @Override
    public HashtagCommand parse(String input) throws ParseException {
        checkArgument(HashtagCommandParser.isHashtagCommand(input), MESSAGE_CONSTRAINT);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(trimAndAddOneLeadingSpace(input), PREFIX_HASHTAG);

        String tagName = argMultimap.getValue(PREFIX_HASHTAG).get();
        if (tagName.isBlank()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, HashtagCommand.MESSAGE_USAGE));
        }

        Tag newTag = ParserUtil.parseTag(tagName);
        return new HashtagCommand(new PersonWithTagPredicate(newTag));
    }

    /**
     * Returns true if the given input string is a hashtag command (any string that starts with #).
     *
     * @param input the input string to verify.
     * @return true if the given input string is a hashtag command (any string that starts with #).
     */
    public static boolean isHashtagCommand(String input) {
        requireNonNull(input);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(trimAndAddOneLeadingSpace(input), PREFIX_HASHTAG);
        return argMultimap.getValue(PREFIX_HASHTAG).isPresent() && argMultimap.getPreamble().isEmpty();
    }

    /**
     * Trims the input string and add a leading space, used for {@code ArgumentTokenizer#tokenize(String, Prefix...)},
     * that requires its {@code String} input to be preceding with a space.
     *
     * @param input the {@code String} to be processed.
     * @return trimmed input with one leading space.
     * @see ArgumentTokenizer#tokenize(String, Prefix...)
     */
    private static String trimAndAddOneLeadingSpace(String input) {
        requireNonNull(input);
        String trimmedInput = input.trim();
        return " " + trimmedInput;
    }
}
