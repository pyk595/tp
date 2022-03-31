package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.ListContactRemindersCommand;

public class ListContactRemindersCommandParserTest {
    private static final Index validIndex = INDEX_FIRST_PERSON;
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListContactRemindersCommand.MESSAGE_USAGE);
    private final ListContactRemindersCommandParser commandParser = new ListContactRemindersCommandParser();

    @Test
    public void parse_nullInput_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> commandParser.parse(null));
    }

    @Test
    public void parse_noIndex_throwParseException() {
        assertParseFailure(commandParser, ListContactRemindersCommand.COMMAND_WORD, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validInputs_success() {
        String userInput = String.valueOf(validIndex.getOneBased());

        ListContactRemindersCommand expectedCommand = new ListContactRemindersCommand(INDEX_FIRST_PERSON);
        assertParseSuccess(commandParser, userInput, expectedCommand);
    }
}
