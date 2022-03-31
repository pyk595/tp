package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_CONTACTED_INFO;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteContactedInfoCommand;

public class DeleteContactedInfoCommandParserTest {
    private static final String CONTACTED_INFO_EMPTY = " " + PREFIX_DELETE_CONTACTED_INFO;
    private static final String CONTACTED_INFO = " " + PREFIX_DELETE_CONTACTED_INFO + "1";

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteContactedInfoCommand.MESSAGE_USAGE);

    private final DeleteContactedInfoCommandParser parser = new DeleteContactedInfoCommandParser();

    @Test
    public void parse_noTag_failure() {
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_noIndex_failure() {
        assertParseFailure(parser, CONTACTED_INFO, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, CONTACTED_INFO_EMPTY + "test", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, CONTACTED_INFO_EMPTY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + CONTACTED_INFO, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "string" + CONTACTED_INFO, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "*(" + CONTACTED_INFO, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "*(12sz" + CONTACTED_INFO, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "0" + CONTACTED_INFO, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "-1" + CONTACTED_INFO, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1 1" + CONTACTED_INFO, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_additionalPrefix_failure() {
        assertParseFailure(parser, PREFIX_ADDRESS + "address " + INDEX_FIRST_PERSON.getOneBased()
                + CONTACTED_INFO, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + CONTACTED_INFO
                + EMAIL_DESC_AMY, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_PHONE + "phone"
                + CONTACTED_INFO, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index index = Index.fromOneBased(1);
        // basic format
        DeleteContactedInfoCommand expectedCommand =
                new DeleteContactedInfoCommand(INDEX_FIRST_PERSON, index);
        assertParseSuccess(parser, INDEX_FIRST_PERSON.getOneBased() + CONTACTED_INFO, expectedCommand);

        // trailing and leading whitespaces
        expectedCommand = new DeleteContactedInfoCommand(INDEX_FIRST_PERSON, index);
        assertParseSuccess(parser, " \n \t  " + INDEX_FIRST_PERSON.getOneBased() + CONTACTED_INFO_EMPTY
                + "1 " + " \n \t  ", expectedCommand);

        // leading spaces in tag
        expectedCommand = new DeleteContactedInfoCommand(INDEX_FIRST_PERSON, index);
        assertParseSuccess(parser, "  \n \t " + INDEX_FIRST_PERSON.getOneBased()
                + CONTACTED_INFO_EMPTY + " \n \t  "
                + " 1 " + " \n \t  ", expectedCommand);

    }
}
