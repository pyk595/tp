package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACTED_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACTED_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddContactedInfoCommand;
import seedu.address.model.contactedinfo.ContactedInfo;
import seedu.address.model.date.RecentDate;
import seedu.address.model.description.Description;

public class AddContactedInfoCommandParserTest {

    private static final String RECENT_DATE_EMPTY = " " + PREFIX_CONTACTED_DATE;
    private static final String RECENT_DESC_EMPTY = " " + PREFIX_CONTACTED_DESC;
    private final AddContactedInfoCommandParser parser = new AddContactedInfoCommandParser();
    private final String nonEmptyDate = "2020-02-02";
    private final String nonEmptyDesc = "Meeting";
    private final String validDate = RECENT_DATE_EMPTY + nonEmptyDate;
    private final String validDesc = RECENT_DESC_EMPTY + nonEmptyDesc;

    @Test
    public void parse_indexSpecified_success() {
        // basic format
        AddContactedInfoCommand expectedCommand = new AddContactedInfoCommand(INDEX_FIRST_PERSON,
                new ContactedInfo(RecentDate.parse(nonEmptyDate), new Description(nonEmptyDesc)));
        assertParseSuccess(parser, INDEX_FIRST_PERSON.getOneBased() + validDate + validDesc, expectedCommand);

        // trailing and leading whitespaces
        expectedCommand = new AddContactedInfoCommand(INDEX_FIRST_PERSON,
                new ContactedInfo(RecentDate.parse(nonEmptyDate), new Description(nonEmptyDesc)));
        assertParseSuccess(parser,
                " \n \t  " + INDEX_FIRST_PERSON.getOneBased() + validDate + validDesc + " \n \t  ",
                expectedCommand);

        // leading spaces
        expectedCommand = new AddContactedInfoCommand(INDEX_FIRST_PERSON,
                new ContactedInfo(RecentDate.parse(nonEmptyDate), new Description(nonEmptyDesc)));
        assertParseSuccess(
                parser,
                "\n \t "
                + INDEX_FIRST_PERSON.getOneBased()
                + " \n \t" + validDate + " \n \t" + validDesc + " \n \t",
                expectedCommand);
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
        assertParseFailure(parser, "1" + RECENT_DATE_EMPTY + RECENT_DESC_EMPTY,
                ContactedInfo.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + RECENT_DATE_EMPTY + "hello " + RECENT_DESC_EMPTY + "meet up",
                ContactedInfo.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + RECENT_DATE_EMPTY + RECENT_DESC_EMPTY + " ",
                ContactedInfo.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void constructor_invalidInputs_throwsInvalidCommandFormat() {
        assertParseFailure(parser, "1" + "test test" + validDate + validDesc + " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContactedInfoCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "test test 1" + "test test" + validDate + validDesc + " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContactedInfoCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleDates_acceptsLast() {
        String firstDate = "2020-02-02";
        String secondDate = "2020-03-01";
        String thirdDate = "2020-04-03";

        // two same dates
        AddContactedInfoCommand expectedCommand = new AddContactedInfoCommand(INDEX_FIRST_PERSON,
                new ContactedInfo(RecentDate.parse(firstDate), new Description(nonEmptyDesc)));
        assertParseSuccess(parser,
                INDEX_FIRST_PERSON.getOneBased()
                + RECENT_DATE_EMPTY + firstDate + RECENT_DATE_EMPTY + firstDate
                + validDesc,
                expectedCommand);

        // two different dates
        expectedCommand = new AddContactedInfoCommand(INDEX_FIRST_PERSON,
                new ContactedInfo(RecentDate.parse(secondDate), new Description(nonEmptyDesc)));
        assertParseSuccess(parser,
                INDEX_FIRST_PERSON.getOneBased()
                + RECENT_DATE_EMPTY + firstDate + RECENT_DATE_EMPTY + secondDate
                + validDesc,
                expectedCommand);


        // three different dates
        expectedCommand = new AddContactedInfoCommand(INDEX_FIRST_PERSON,
                new ContactedInfo(RecentDate.parse(thirdDate), new Description(nonEmptyDesc)));
        assertParseSuccess(parser,
                INDEX_FIRST_PERSON.getOneBased()
                + RECENT_DATE_EMPTY + firstDate + RECENT_DATE_EMPTY + secondDate + RECENT_DATE_EMPTY + thirdDate
                + validDesc,
                expectedCommand);
    }

    @Test
    public void parse_multipleDesc_acceptsLast() {
        String firstDesc = "phone call";
        String secondDesc = "meet up";
        String thirdDesc = "coffee break";

        // two same desc
        AddContactedInfoCommand expectedCommand = new AddContactedInfoCommand(INDEX_FIRST_PERSON,
                new ContactedInfo(RecentDate.parse(nonEmptyDate), new Description(firstDesc)));
        assertParseSuccess(parser, INDEX_FIRST_PERSON.getOneBased()
                + RECENT_DATE_EMPTY + nonEmptyDate
                + RECENT_DESC_EMPTY + firstDesc + RECENT_DESC_EMPTY + firstDesc,
                expectedCommand);

        // two different desc
        expectedCommand = new AddContactedInfoCommand(INDEX_FIRST_PERSON,
                new ContactedInfo(RecentDate.parse(nonEmptyDate), new Description(secondDesc)));
        assertParseSuccess(
                parser,
                INDEX_FIRST_PERSON.getOneBased()
                + RECENT_DATE_EMPTY + nonEmptyDate
                + RECENT_DESC_EMPTY + firstDesc + RECENT_DESC_EMPTY + secondDesc,
                expectedCommand);


        // three different desc
        expectedCommand = new AddContactedInfoCommand(INDEX_FIRST_PERSON,
                new ContactedInfo(RecentDate.parse(nonEmptyDate), new Description(thirdDesc)));
        assertParseSuccess(
                parser,
                INDEX_FIRST_PERSON.getOneBased()
                + RECENT_DATE_EMPTY + nonEmptyDate
                + RECENT_DESC_EMPTY + firstDesc + RECENT_DESC_EMPTY + secondDesc + RECENT_DESC_EMPTY + thirdDesc,
                expectedCommand);
    }

    @Test
    public void parse_multipleDateAndDesc_acceptsLast() {
        String firstDate = "2020-02-02";
        String secondDate = "2020-03-01";
        String thirdDate = "2020-04-03";

        String firstDesc = "phone call";
        String secondDesc = "meet up";
        String thirdDesc = "coffee break";

        // two same date and desc
        AddContactedInfoCommand expectedCommand = new AddContactedInfoCommand(INDEX_FIRST_PERSON,
                new ContactedInfo(RecentDate.parse(firstDate), new Description(firstDesc)));
        assertParseSuccess(
                parser,
                INDEX_FIRST_PERSON.getOneBased()
                + RECENT_DATE_EMPTY + firstDate + RECENT_DATE_EMPTY + firstDate
                + RECENT_DESC_EMPTY + firstDesc + RECENT_DESC_EMPTY + firstDesc,
                expectedCommand);

        // two different date and desc
        expectedCommand = new AddContactedInfoCommand(INDEX_FIRST_PERSON,
                new ContactedInfo(RecentDate.parse(secondDate), new Description(secondDesc)));
        assertParseSuccess(
                parser,
                INDEX_FIRST_PERSON.getOneBased()
                + RECENT_DATE_EMPTY + firstDate + RECENT_DATE_EMPTY + secondDate
                + RECENT_DESC_EMPTY + firstDesc + RECENT_DESC_EMPTY + secondDesc,
                expectedCommand);


        // three different date and desc
        expectedCommand = new AddContactedInfoCommand(INDEX_FIRST_PERSON,
                new ContactedInfo(RecentDate.parse(thirdDate), new Description(thirdDesc)));
        assertParseSuccess(
                parser,
                INDEX_FIRST_PERSON.getOneBased()
                + RECENT_DATE_EMPTY + firstDate + RECENT_DATE_EMPTY + secondDate + RECENT_DATE_EMPTY + thirdDate
                + RECENT_DESC_EMPTY + firstDesc + RECENT_DESC_EMPTY + secondDesc + RECENT_DESC_EMPTY + thirdDesc,
                expectedCommand);
    }
}
