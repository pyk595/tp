package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACTED_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACTED_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddContactedInfoCommand;
import seedu.address.model.contactedinfo.ContactedInfo;

public class AddContactedInfoCommandParserTest {
    private final AddContactedCommandParser parser = new AddContactedCommandParser();
    private final String nonEmptyDate = "2020-02-02";
    private final String nonEmptyDesc = "Meeting";

    @Test
    public void parse_indexSpecified_success() {

        // have date and description
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_CONTACTED_DATE + nonEmptyDate + " "
                + PREFIX_CONTACTED_DESC + nonEmptyDesc;
        AddContactedInfoCommand expectedCommand = new AddContactedInfoCommand(INDEX_FIRST_PERSON,
                new ContactedInfo(nonEmptyDate, nonEmptyDesc));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContactedInfoCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, AddContactedInfoCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, AddContactedInfoCommand.COMMAND_WORD + " "
                + nonEmptyDate + " " + nonEmptyDesc, expectedMessage);
    }
}
