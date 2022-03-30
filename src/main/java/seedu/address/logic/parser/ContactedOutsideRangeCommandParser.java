package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ContactedOutsideRangeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonOutsideDateRangePredicate;

/**
 * Parses input arguments and creates a new {@code ContactedOutsideRangeCommand} object
 */
public class ContactedOutsideRangeCommandParser implements Parser<ContactedOutsideRangeCommand> {
    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput the number of days represented in String format.
     * @throws ParseException if {@code userInput} does not conform the expected format.
     */
    @Override
    public ContactedOutsideRangeCommand parse(String userInput) throws ParseException {
        try {
            int days = ParserUtil.parseDays(userInput);
            return new ContactedOutsideRangeCommand(new PersonOutsideDateRangePredicate(days));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedOutsideRangeCommand.MESSAGE_USAGE), pe);

        }
    }
}
