package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_REMINDER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteReminderCommand;

public class DeleteReminderCommandParserTest {
    private static final Index validIndex = INDEX_FIRST_PERSON;
    private static final String EMPTY_REMINDER_INDEX = " " + PREFIX_DELETE_REMINDER;
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteReminderCommand.MESSAGE_USAGE);
    private final DeleteReminderCommandParser commandParser = new DeleteReminderCommandParser();
    private final String sampleReminderIndex = "1";
    private final String validReminderIndex = EMPTY_REMINDER_INDEX + " " + sampleReminderIndex;

    @Test
    public void parse_nullInput_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> commandParser.parse(null));
    }

    @Test
    public void parse_missingFields_throwParseException() {
        String index = "1";

        // no parameter
        assertParseFailure(commandParser, DeleteReminderCommand.COMMAND_WORD, MESSAGE_INVALID_FORMAT);

        // no index
        assertParseFailure(commandParser, DeleteReminderCommand.COMMAND_WORD
                + validReminderIndex, MESSAGE_INVALID_FORMAT);

        // no reminder index
        assertParseFailure(commandParser, DeleteReminderCommand.COMMAND_WORD + index
                + EMPTY_REMINDER_INDEX, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validInputs_success() {
        String userInput = validIndex.getOneBased()
                + validReminderIndex;

        DeleteReminderCommand expectedCommand = new DeleteReminderCommand(INDEX_FIRST_PERSON, INDEX_FIRST_PERSON);
        assertParseSuccess(commandParser, userInput, expectedCommand);
    }
}
