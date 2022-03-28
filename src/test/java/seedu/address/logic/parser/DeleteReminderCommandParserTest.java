package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER_DESCRIPTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteReminderCommand;
import seedu.address.model.date.ReminderDate;
import seedu.address.model.reminder.ReminderDescription;

public class DeleteReminderCommandParserTest {
    private static final Index validIndex = INDEX_FIRST_PERSON;
    private static final String EMPTY_REMINDER_DESCRIPTION = " " + PREFIX_REMINDER_DESCRIPTION;
    private static final String EMPTY_REMINDER_DATE = " " + PREFIX_REMINDER_DATE;
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteReminderCommand.MESSAGE_USAGE);
    private final DeleteReminderCommandParser commandParser = new DeleteReminderCommandParser();
    private final String sampleReminderDescription = "test";
    private final String sampleReminderDate = "2020-01-01";
    private final String validReminderDescription = EMPTY_REMINDER_DESCRIPTION + " " + sampleReminderDescription;
    private final String validReminderDate = EMPTY_REMINDER_DATE + " " + sampleReminderDate;

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
                + validReminderDescription + validReminderDate, MESSAGE_INVALID_FORMAT);

        // no description and date
        assertParseFailure(commandParser, DeleteReminderCommand.COMMAND_WORD + index
                + EMPTY_REMINDER_DESCRIPTION + EMPTY_REMINDER_DATE, MESSAGE_INVALID_FORMAT);

        // no date
        assertParseFailure(commandParser, DeleteReminderCommand.COMMAND_WORD + index
                + validReminderDescription + EMPTY_REMINDER_DATE, MESSAGE_INVALID_FORMAT);

        // no description
        assertParseFailure(commandParser, DeleteReminderCommand.COMMAND_WORD + index
                + EMPTY_REMINDER_DESCRIPTION + validReminderDate, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validInputs_success() {
        String userInput = validIndex.getOneBased()
                + validReminderDescription
                + validReminderDate;

        DeleteReminderCommand expectedCommand = new DeleteReminderCommand(INDEX_FIRST_PERSON,
                new ReminderDescription("test"), new ReminderDate(LocalDate.of(2020, 1, 1)));
        assertParseSuccess(commandParser, userInput, expectedCommand);
    }
}
