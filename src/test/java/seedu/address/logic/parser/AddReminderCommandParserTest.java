package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER_DESCRIPTION;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddReminderCommand;
import seedu.address.model.date.ReminderDate;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderDescription;

public class AddReminderCommandParserTest {

    private static final Index validIndex = INDEX_FIRST_PERSON;
    private static final String EMPTY_REMINDER_DESCRIPTION = " " + PREFIX_REMINDER_DESCRIPTION;
    private static final String EMPTY_REMINDER_DATE = " " + PREFIX_REMINDER_DATE;
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddReminderCommand.MESSAGE_USAGE);
    private final AddReminderCommandParser commandParser = new AddReminderCommandParser();
    private final String sampleReminderDescription = "test";
    private final String validReminderDescription = EMPTY_REMINDER_DESCRIPTION + " " + sampleReminderDescription;
    private LocalDate sampleReminderDate;
    private String validReminderDate;

    @BeforeEach
    public void setUp() {
        sampleReminderDate = LocalDate.now().plusDays(1);
        validReminderDate = EMPTY_REMINDER_DATE + " " + sampleReminderDate;
    }

    @Test
    public void parse_nullInput_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> commandParser.parse(null));
    }

    @Test
    public void parse_missingFields_throwParseException() {
        String index = "1";

        // no parameter
        assertParseFailure(commandParser, AddReminderCommand.COMMAND_WORD, MESSAGE_INVALID_FORMAT);

        // no index
        assertParseFailure(commandParser, AddReminderCommand.COMMAND_WORD
                + validReminderDescription + validReminderDate, MESSAGE_INVALID_FORMAT);

        // no description and date
        assertParseFailure(commandParser, AddReminderCommand.COMMAND_WORD + index
                        + EMPTY_REMINDER_DESCRIPTION + EMPTY_REMINDER_DATE, MESSAGE_INVALID_FORMAT);

        // no date
        assertParseFailure(commandParser, AddReminderCommand.COMMAND_WORD + index
                + validReminderDescription + EMPTY_REMINDER_DATE, MESSAGE_INVALID_FORMAT);

        // no description
        assertParseFailure(commandParser, AddReminderCommand.COMMAND_WORD + index
                + EMPTY_REMINDER_DESCRIPTION + validReminderDate, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validInputs_success() {
        String userInput = validIndex.getOneBased()
                + validReminderDescription
                + validReminderDate;

        AddReminderCommand expectedCommand = new AddReminderCommand(INDEX_FIRST_PERSON,
                new Reminder(new ReminderDescription("test"), new ReminderDate(LocalDate.now().plusDays(1))));
        assertParseSuccess(commandParser, userInput, expectedCommand);
    }
}
