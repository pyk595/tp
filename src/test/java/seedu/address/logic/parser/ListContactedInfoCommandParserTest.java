package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListContactedInfoCommand;

public class ListContactedInfoCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListContactedInfoCommand.MESSAGE_USAGE);

    private final ListContactedInfoCommandParser parser = new ListContactedInfoCommandParser();

    @Test
    public void parse_indexSpecified_success() {
        // Expected case
        ListContactedInfoCommand expectedCommand = new ListContactedInfoCommand(INDEX_FIRST_PERSON);
        assertParseSuccess(parser, " 1 ", expectedCommand);

        // trailing and leading whitespaces
        expectedCommand = new ListContactedInfoCommand(INDEX_FIRST_PERSON);
        assertParseSuccess(parser, " \n \t  " + " 1 " + " \n \t  ", expectedCommand);

        // leading spaces in tag
        expectedCommand = new ListContactedInfoCommand(INDEX_FIRST_PERSON);
        assertParseSuccess(parser, "  \n \t " + " 1 " + " \n \t  ", expectedCommand);
    }

    @Test
    public void parse_noIndex_failure() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, "string", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "*(", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "*(12sz", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "0", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "-1", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1 1", MESSAGE_INVALID_FORMAT);
    }
}
