package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.model.tag.Tag;

public class DeleteTagCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTagCommand.MESSAGE_USAGE);

    private final DeleteTagCommandParser parser = new DeleteTagCommandParser();

    @Test
    public void parse_noTag_failure() {
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidTag_failure() {
        assertParseFailure(parser, "1" + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + "hubby and me", Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + "hubby and me" + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_noIndex_failure() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + TAG_DESC_FRIEND, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, TAG_EMPTY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TAG_DESC_FRIEND, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "string" + TAG_DESC_FRIEND, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "*(" + TAG_DESC_FRIEND, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "*(12sz" + TAG_DESC_FRIEND, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "0" + TAG_DESC_FRIEND, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "-1" + TAG_DESC_FRIEND, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1 1" + TAG_DESC_FRIEND, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_additionalPrefix_failure() {
        assertParseFailure(parser, PREFIX_ADDRESS + "address " + INDEX_FIRST_PERSON.getOneBased()
                + TAG_DESC_FRIEND, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + TAG_DESC_FRIEND
                + EMAIL_DESC_AMY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_PHONE + "phone"
                + TAG_DESC_FRIEND, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        // basic format
        DeleteTagCommand expectedCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, new Tag(VALID_TAG_FRIEND));
        assertParseSuccess(parser, INDEX_FIRST_PERSON.getOneBased() + TAG_EMPTY + VALID_TAG_FRIEND, expectedCommand);

        // trailing and leading whitespaces
        expectedCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, new Tag(VALID_TAG_FRIEND));
        assertParseSuccess(parser, " \n \t  " + INDEX_FIRST_PERSON.getOneBased() + TAG_EMPTY
                + VALID_TAG_FRIEND + " \n \t  ", expectedCommand);

        // leading spaces in tag
        expectedCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, new Tag(VALID_TAG_FRIEND));
        assertParseSuccess(parser, "  \n \t " + INDEX_FIRST_PERSON.getOneBased() + TAG_EMPTY + " \n \t  "
                + VALID_TAG_FRIEND + " \n \t  ", expectedCommand);
    }

    @Test
    public void parse_multipleTag_acceptsLast() {
        // two different tags
        DeleteTagCommand expectedCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, new Tag(VALID_TAG_FRIEND));
        assertParseSuccess(parser, INDEX_FIRST_PERSON.getOneBased() + TAG_DESC_HUSBAND
                + TAG_DESC_FRIEND, expectedCommand);

        // two same tags
        expectedCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, new Tag(VALID_TAG_FRIEND));
        assertParseSuccess(parser, INDEX_FIRST_PERSON.getOneBased() + TAG_DESC_FRIEND
                + TAG_DESC_FRIEND, expectedCommand);

        // three tags
        expectedCommand = new DeleteTagCommand(INDEX_FIRST_PERSON, new Tag(VALID_TAG_HUSBAND));
        assertParseSuccess(parser, INDEX_FIRST_PERSON.getOneBased() + TAG_DESC_FRIEND
                + TAG_DESC_FRIEND + TAG_DESC_HUSBAND, expectedCommand);
    }
}
