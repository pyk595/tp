package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_ARGUMENTS;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACTED_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONTACTED_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddContactedInfoCommand;
import seedu.address.logic.commands.AddTagCommand;
import seedu.address.logic.commands.BirthdayCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ContactedOutsideRangeCommand;
import seedu.address.logic.commands.ContactedWithinRangeCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteTagCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HashtagCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListTagsCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.contactedinfo.ContactedInfo;
import seedu.address.model.date.RecentDate;
import seedu.address.model.description.Description;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonWithTagPredicate;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().addDefaultContactedInfo().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertThrows(ParseException.class, MESSAGE_INVALID_ARGUMENTS, ()
            -> parser.parseCommand(ExitCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().addDefaultContactedInfo().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertThrows(ParseException.class, MESSAGE_INVALID_ARGUMENTS, ()
            -> parser.parseCommand(ExitCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_hashtag() throws Exception {
        Tag tag = new Tag(VALID_TAG_HUSBAND);
        HashtagCommand command = (HashtagCommand) parser.parseCommand(HashtagCommand.COMMAND_WORD + VALID_TAG_HUSBAND);
        assertEquals(new HashtagCommand(new PersonWithTagPredicate(tag)), command);
    }

    @Test
    public void parseCommand_addTag() throws Exception {
        Tag tag = new Tag(VALID_TAG_FRIEND);
        AddTagCommand command = (AddTagCommand) parser.parseCommand(AddTagCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_TAG + VALID_TAG_FRIEND);
        assertEquals(new AddTagCommand(INDEX_FIRST_PERSON, tag), command);
    }

    @Test
    public void parseCommand_deleteTag() throws Exception {
        Tag tag = new Tag(VALID_TAG_FRIEND);
        DeleteTagCommand command = (DeleteTagCommand) parser.parseCommand(DeleteTagCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_TAG + VALID_TAG_FRIEND);
        assertEquals(new DeleteTagCommand(INDEX_FIRST_PERSON, tag), command);
    }

    @Test
    public void parseCommand_tags() throws Exception {
        assertTrue(parser.parseCommand(ListTagsCommand.COMMAND_WORD) instanceof ListTagsCommand);
        assertThrows(ParseException.class, MESSAGE_INVALID_ARGUMENTS, ()
            -> parser.parseCommand(ListTagsCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertThrows(ParseException.class, MESSAGE_INVALID_ARGUMENTS, ()
            -> parser.parseCommand(HelpCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertThrows(ParseException.class, MESSAGE_INVALID_ARGUMENTS, ()
            -> parser.parseCommand(ListCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_birthday() throws Exception {
        assertTrue(parser.parseCommand(BirthdayCommand.COMMAND_WORD) instanceof BirthdayCommand);
        assertThrows(ParseException.class, MESSAGE_INVALID_ARGUMENTS, ()
            -> parser.parseCommand(BirthdayCommand.COMMAND_WORD + " 3"));
    }

    @Test
    public void parseCommand_contactedWithinRange() throws Exception {
        assertTrue(
                parser.parseCommand(ContactedWithinRangeCommand.COMMAND_WORD + " 5")
                        instanceof ContactedWithinRangeCommand);
    }

    @Test
    public void parseCommand_contactedOutsideRange() throws Exception {
        assertTrue(
                parser.parseCommand(ContactedOutsideRangeCommand.COMMAND_WORD + " 5")
                        instanceof ContactedOutsideRangeCommand);
    }

    @Test
    public void parseCommand_contacted() throws Exception {
        final String date = "2020-02-02";
        final String desc = "Meeting";
        final RecentDate recentDate = RecentDate.parse(date);
        final Description description = new Description(desc);
        AddContactedInfoCommand command = (AddContactedInfoCommand) parser.parseCommand(
                AddContactedInfoCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " "
                + PREFIX_CONTACTED_DATE + date + " "
                + PREFIX_CONTACTED_DESC + desc);
        assertEquals(new AddContactedInfoCommand(INDEX_FIRST_PERSON,
                new ContactedInfo(recentDate, description)), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, ()
            -> parser.parseCommand("unknownCommand"));
    }
}
