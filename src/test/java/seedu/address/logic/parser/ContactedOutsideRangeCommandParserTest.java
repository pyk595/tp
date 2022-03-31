package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ContactedOutsideRangeCommand;
import seedu.address.model.person.PersonOutsideDateRangePredicate;

class ContactedOutsideRangeCommandParserTest {

    private final ContactedOutsideRangeCommandParser parser = new ContactedOutsideRangeCommandParser();

    @Test
    public void parse_notContactedOutsideRangeCommand_throwsParseException() {
        assertParseFailure(parser, "random",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedOutsideRangeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "random input",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedOutsideRangeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "   ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedOutsideRangeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedOutsideRangeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_notPositiveInteger_throwsParseException() {
        assertParseFailure(parser, "-1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedOutsideRangeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1.0",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedOutsideRangeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "+1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedOutsideRangeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 n",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedOutsideRangeCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1 and one",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedOutsideRangeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_positiveInteger_success() {
        int days = 0;
        ContactedOutsideRangeCommand expectedCommand =
               new ContactedOutsideRangeCommand(new PersonOutsideDateRangePredicate(days));
        assertParseSuccess(parser, "0", expectedCommand);
        assertParseSuccess(parser, "00", expectedCommand);
        days = 1;
        expectedCommand = new ContactedOutsideRangeCommand(new PersonOutsideDateRangePredicate(days));
        assertParseSuccess(parser, "1", expectedCommand);
        assertParseSuccess(parser, " 1", expectedCommand);
        assertParseSuccess(parser, " 1 ", expectedCommand);
        assertParseSuccess(parser, "01", expectedCommand);
    }

}
