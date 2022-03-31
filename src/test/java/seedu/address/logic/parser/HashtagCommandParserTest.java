package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.HashtagCommand;
import seedu.address.model.person.PersonWithTagPredicate;
import seedu.address.model.tag.Tag;

class HashtagCommandParserTest {

    private final HashtagCommandParser parser = new HashtagCommandParser();

    @Test
    public void parse_notHashtagCommand_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> parser.parse("random"));
        assertThrows(IllegalArgumentException.class, () -> parser.parse("random input"));
        assertThrows(IllegalArgumentException.class, () -> parser.parse("   "));
        assertThrows(IllegalArgumentException.class, () -> parser.parse(""));
    }

    @Test
    public void parse_notAlphanumeric_throwsParseException() {
        assertParseFailure(parser, "#not alphanumeric", Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "#no#$@", Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "#",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, HashtagCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsHashtagCommand() {
        HashtagCommand expectedHashtagCommand =
                new HashtagCommand(new PersonWithTagPredicate(new Tag("friends")));

        // basic format
        assertParseSuccess(parser, "#friends", expectedHashtagCommand);

        // space before tag
        assertParseSuccess(parser, "# friends", expectedHashtagCommand);

        // leading and trailing spaces
        assertParseSuccess(parser, " \n \t #friends \n \t ", expectedHashtagCommand);
    }

    @Test
    public void isHashtagCommand_isHashtagCommand_returnsTrue() {
        assertTrue(HashtagCommandParser.isHashtagCommand(" #test"));
        assertTrue(HashtagCommandParser.isHashtagCommand("#test"));
        assertTrue(HashtagCommandParser.isHashtagCommand("#test 123"));
    }

    @Test
    public void isHashtagCommand_notHashtagCommand_returnFalse() {
        assertFalse(HashtagCommandParser.isHashtagCommand("find"));
        assertFalse(HashtagCommandParser.isHashtagCommand("notTag#test"));
    }

    @Test
    public void isHashtagCommand_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> HashtagCommandParser.isHashtagCommand(null));
    }
}
