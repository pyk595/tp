package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ContactedWithinRangeCommand;
import seedu.address.model.person.PersonWithinDateRangePredicate;


class ContactedWithinRangeCommandParserTest {

    private final ContactedWithinRangeCommandParser parser = new ContactedWithinRangeCommandParser();

    @Test
    public void parse_notContactedWithinRangeCommand_throwsParseException() {
        assertParseFailure(parser, "random",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedWithinRangeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "random input",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedWithinRangeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedWithinRangeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedWithinRangeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_notPositiveInteger_throwsParseException() {
        assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedWithinRangeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "+1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedWithinRangeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1.0",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedWithinRangeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 n",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedWithinRangeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 and one",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedWithinRangeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_positiveInteger_success() {
        int days = 0;
        ContactedWithinRangeCommand expectedCommand =
                new ContactedWithinRangeCommand(new PersonWithinDateRangePredicate(days));
        assertParseSuccess(parser, "0", expectedCommand);
        assertParseSuccess(parser, "00", expectedCommand);
        days = 1;
        expectedCommand = new ContactedWithinRangeCommand(new PersonWithinDateRangePredicate(days));
        assertParseSuccess(parser, "1", expectedCommand);
        assertParseSuccess(parser, " 1", expectedCommand);
        assertParseSuccess(parser, " 1 ", expectedCommand);
        assertParseSuccess(parser, "01", expectedCommand);
    }

}
