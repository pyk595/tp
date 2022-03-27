package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ContactedWithinRangeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.PersonWithinDateRangePredicate;

/**
 * Parses input arguments and creates a new {@code ContactedWithinRangeCommand} object
 */
public class ContactedWithinRangeCommandParser implements Parser<ContactedWithinRangeCommand> {


    /**
     * Parses {@code userInput} into a command and returns it.
     *
     * @param userInput the number of days represented in String format.
     * @throws ParseException if {@code userInput} does not conform the expected format.
     */
    @Override
    public ContactedWithinRangeCommand parse(String userInput) throws ParseException {
        try {
            int days = ParserUtil.parseDays(userInput);
            return new ContactedWithinRangeCommand(new PersonWithinDateRangePredicate(days));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ContactedWithinRangeCommand.MESSAGE_USAGE), pe);

        }
    }
}
