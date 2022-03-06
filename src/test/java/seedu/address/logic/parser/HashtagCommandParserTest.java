package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HashtagCommand;
import seedu.address.model.person.PersonContainsTagPredicate;
import seedu.address.model.tag.Tag;

class HashtagCommandParserTest {

    private HashtagCommandParser parser = new HashtagCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "   ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, HashtagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_notAlphanumeric_throwsParseException() {
        assertParseFailure(parser, "not alphanumeric", Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    void parse_validArgs_returnsHashtagCommand() {
        HashtagCommand expectedHashtagCommand =
                new HashtagCommand(new PersonContainsTagPredicate(new Tag("friends")));

        // vanilla
        assertParseSuccess(parser, "friends", expectedHashtagCommand);

        // leading and trailing spaces
        assertParseSuccess(parser, " \n \t friends \n \t ", expectedHashtagCommand);
    }
}
