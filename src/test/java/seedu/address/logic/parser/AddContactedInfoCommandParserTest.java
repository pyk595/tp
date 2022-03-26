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
import seedu.address.model.date.RecentDate;
import seedu.address.model.description.Description;

public class AddContactedInfoCommandParserTest {

    private static final String RECENT_DATE_EMPTY = " " + PREFIX_CONTACTED_DATE;
    private static final String RECENT_DESC_EMPTY = " " + PREFIX_CONTACTED_DESC;
    private final AddContactedCommandParser parser = new AddContactedCommandParser();
    private final String nonEmptyDate = "2020-02-02";
    private final String nonEmptyDesc = "Meeting";
    private final String validDate = RECENT_DATE_EMPTY + nonEmptyDate;
    private final String validDesc = RECENT_DESC_EMPTY + nonEmptyDesc;

    @Test
    public void parse_indexSpecified_success() {

        // have date and description
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput =
                targetIndex.getOneBased()
                + " "
                + PREFIX_CONTACTED_DATE
                + nonEmptyDate
                + " "
                + PREFIX_CONTACTED_DESC + nonEmptyDesc;

        AddContactedInfoCommand expectedCommand = new AddContactedInfoCommand(INDEX_FIRST_PERSON,
                new ContactedInfo(RecentDate.parse(nonEmptyDate), new Description(nonEmptyDesc)));
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingCompulsoryField_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContactedInfoCommand.MESSAGE_USAGE);

        // no parameters
        assertParseFailure(parser, AddContactedInfoCommand.COMMAND_WORD, expectedMessage);

        // no index
        assertParseFailure(parser, AddContactedInfoCommand.COMMAND_WORD
                + " "
                + validDate
                + " "
                + validDesc,
                expectedMessage);

        //no date
        assertParseFailure(parser, AddContactedInfoCommand.COMMAND_WORD
                + " 1 "
                + validDesc,
                expectedMessage);

        //no description
        assertParseFailure(parser, AddContactedInfoCommand.COMMAND_WORD
                + " 1 "
                + validDate,
                expectedMessage);

    }

    @Test
    public void constructor_invalidInputs_throwsIllegalArgumentException() {
        String description = "";
        assertParseFailure(parser, "1" + RECENT_DATE_EMPTY + RECENT_DESC_EMPTY,
                ContactedInfo.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + RECENT_DATE_EMPTY + "hello " + RECENT_DESC_EMPTY + "meet up",
                ContactedInfo.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + RECENT_DATE_EMPTY + RECENT_DESC_EMPTY + " ",
                ContactedInfo.MESSAGE_CONSTRAINTS);
    }
}
