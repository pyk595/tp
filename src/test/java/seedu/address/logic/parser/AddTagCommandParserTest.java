package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddTagCommand;
import seedu.address.model.tag.Tag;

class AddTagCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE);

    private final AddTagCommandParser parser = new AddTagCommandParser();

    @Test
    public void parse_noTag_failure() {
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidTag_failure() {
        assertParseFailure(parser, "1" + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + "hubby*", Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + "hubby and me", Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + "hubby and me" + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_noIndex_failure() {
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, TAG_EMPTY, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, TAG_EMPTY + "hubby*", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, TAG_EMPTY + "hubby and me", MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, TAG_EMPTY + "hubby and me" + TAG_EMPTY, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidIndex_failure() {
        assertParseFailure(parser, "string" + TAG_EMPTY + VALID_TAG_FRIEND, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "*(" + TAG_EMPTY + VALID_TAG_FRIEND, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "*(12sz" + TAG_EMPTY + VALID_TAG_FRIEND, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "0" + TAG_EMPTY + VALID_TAG_FRIEND, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "-1" + TAG_EMPTY + VALID_TAG_FRIEND, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, "1 1" + TAG_EMPTY + VALID_TAG_FRIEND, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_additionalPrefix_failure() {
        assertParseFailure(parser, PREFIX_ADDRESS + "address " + INDEX_FIRST_PERSON.getOneBased()
                + TAG_EMPTY + VALID_TAG_FRIEND, MESSAGE_INVALID_FORMAT);
        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + TAG_EMPTY + VALID_TAG_FRIEND
                + PREFIX_EMAIL + "email", Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_PHONE + "phone"
                + TAG_EMPTY + VALID_TAG_FRIEND, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        // basic format
        AddTagCommand expectedCommand = new AddTagCommand(INDEX_FIRST_PERSON, new Tag(VALID_TAG_FRIEND));
        assertParseSuccess(parser, INDEX_FIRST_PERSON.getOneBased() + TAG_EMPTY + VALID_TAG_FRIEND, expectedCommand);

        // trailing and leading whitespaces
        expectedCommand = new AddTagCommand(INDEX_FIRST_PERSON, new Tag(VALID_TAG_FRIEND));
        assertParseSuccess(parser, " \n \t  " + INDEX_FIRST_PERSON.getOneBased() + TAG_EMPTY
                + VALID_TAG_FRIEND + " \n \t  ", expectedCommand);

        // leading spaces in tag
        expectedCommand = new AddTagCommand(INDEX_FIRST_PERSON, new Tag(VALID_TAG_FRIEND));
        assertParseSuccess(parser, "  \n \t " + INDEX_FIRST_PERSON.getOneBased() + TAG_EMPTY + " \n \t  "
                + VALID_TAG_FRIEND + " \n \t  ", expectedCommand);
    }

    @Test
    public void parse_multipleTag_acceptsLast() {
        // two different tags
        AddTagCommand expectedCommand = new AddTagCommand(INDEX_FIRST_PERSON, new Tag(VALID_TAG_FRIEND));
        assertParseSuccess(parser, INDEX_FIRST_PERSON.getOneBased() + TAG_EMPTY + VALID_TAG_HUSBAND
                + TAG_EMPTY + VALID_TAG_FRIEND, expectedCommand);

        // two same tags
        expectedCommand = new AddTagCommand(INDEX_FIRST_PERSON, new Tag(VALID_TAG_FRIEND));
        assertParseSuccess(parser, INDEX_FIRST_PERSON.getOneBased() + TAG_EMPTY + VALID_TAG_FRIEND
                + TAG_EMPTY + VALID_TAG_FRIEND, expectedCommand);

        // three tags
        expectedCommand = new AddTagCommand(INDEX_FIRST_PERSON, new Tag(VALID_TAG_HUSBAND));
        assertParseSuccess(parser, INDEX_FIRST_PERSON.getOneBased() + TAG_EMPTY + VALID_TAG_FRIEND
                + TAG_EMPTY + VALID_TAG_FRIEND + TAG_EMPTY + VALID_TAG_HUSBAND, expectedCommand);
    }
}
