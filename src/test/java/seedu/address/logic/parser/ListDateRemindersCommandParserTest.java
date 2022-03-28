package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER_DATE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ListDateRemindersCommand;
import seedu.address.model.date.ReminderDate;

public class ListDateRemindersCommandParserTest {
    private static final String EMPTY_REMINDER_DATE = " " + PREFIX_REMINDER_DATE;
    private final ListDateRemindersCommandParser commandParser = new ListDateRemindersCommandParser();
    private final String sampleReminderDate = "2020-01-01";
    private final String validReminderDate = EMPTY_REMINDER_DATE + " " + sampleReminderDate;

    @Test
    public void parse_nullInput_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> commandParser.parse(null));
    }

    @Test
    public void parse_validInputs_success() {
        // with date
        ListDateRemindersCommand expectedCommand = new ListDateRemindersCommand(
                new ReminderDate(LocalDate.of(2020, 1, 1)));
        assertParseSuccess(commandParser, validReminderDate, expectedCommand);

        // no date
        ListDateRemindersCommand expectedCommandNoDate = new ListDateRemindersCommand(
                new ReminderDate(LocalDate.now()));
        assertParseSuccess(commandParser, validReminderDate, expectedCommand);
    }
}
