package seedu.address.logic.commands;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.date.BirthDate;
import seedu.address.model.date.RecentDate;
import seedu.address.model.person.*;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderDescription;
import seedu.address.model.reminder.ReminderList;
import seedu.address.model.tag.Tag;

import java.util.List;
import java.util.Set;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER_DESCRIPTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

/**
 * Deletes a reminder from a contact.
 */
public class DeleteReminderCommand extends Command {

    private final Index index;
    private final ReminderDescription reminderDescription;

    public static final String COMMAND_WORD = "forget";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a reminder from an existing contact, "
            + "as specified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_REMINDER_DESCRIPTION + "REMINDER\n"
            + "Example: " + COMMAND_WORD + " 2 "
            + PREFIX_REMINDER_DESCRIPTION + "meeting";

    public static final String MESSAGE_DELETE_REMINDER_SUCCESS = "Added reminder: %1$s";

    /**
     * Creates an DeleteReminderCommand to delete the specified {@code ReminderDescription} from the
     * specified {@code Person}
     * @param index of the person
     * @param reminderDescription of the reminder to be deleted.
     */
    public DeleteReminderCommand(Index index, ReminderDescription reminderDescription) {
        this.index = index;
        this.reminderDescription = reminderDescription;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = lastShownList.get(index.getZeroBased());
        ReminderList reminderList = personToDelete.getReminderList();
        Reminder reminderToDelete = reminderList.find(reminderDescription);

        Name updatedName = personToDelete.getName();
        Phone updatedPhone = personToDelete.getPhone();
        Email updatedEmail = personToDelete.getEmail();
        Address updatedAddress = personToDelete.getAddress();
        BirthDate updatedBirthDate = personToDelete.getBirthDate();
        RecentDate updatedContactedDate = personToDelete.getContactedDate();
        Description updatedContactedDescription = personToDelete.getContactedDesc();
        Set<Tag> updatedTags = personToDelete.getTags();
        ReminderList updatedReminderList = reminderList.delete(reminderToDelete);
        Person updatedPerson = new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedBirthDate,
                updatedContactedDate, updatedContactedDescription, updatedTags, updatedReminderList);

        model.setPerson(personToDelete, updatedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_DELETE_REMINDER_SUCCESS, reminderToDelete));
    }
}
