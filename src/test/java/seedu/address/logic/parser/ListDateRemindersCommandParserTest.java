package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListDateRemindersCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.date.ReminderDate;

public class ListDateRemindersCommandParserTest {
    private static final String EMPTY_REMINDER_DATE = " " + PREFIX_REMINDER_DATE;
    private final ListDateRemindersCommandParser commandParser = new ListDateRemindersCommandParser();
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
    public void parse_validInputs_success() {
        // with date
        ListDateRemindersCommand expectedCommand = new ListDateRemindersCommand(
                new ReminderDate(LocalDate.now().plusDays(1)));
        assertParseSuccess(commandParser, validReminderDate, expectedCommand);

        // no date
        ListDateRemindersCommand expectedCommandNoDate = new ListDateRemindersCommand(
                new ReminderDate(LocalDate.now()));
        assertParseSuccess(commandParser, validReminderDate, expectedCommand);
    }

    @Test
    public void parse_invalidInputs_throwIllegalArgumentException() {
        // random string
        assertThrows(ParseException.class, () -> commandParser.parse("test"));
        assertThrows(ParseException.class, () -> commandParser.parse("1"));
        assertThrows(ParseException.class, () -> commandParser.parse("test123!"));
    }
}
