package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE_REMINDER;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.contactedinfo.ContactedInfo;
import seedu.address.model.date.BirthDate;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.reminder.ReminderList;
import seedu.address.model.tag.Tag;

/**
 * Deletes a reminder from a contact.
 */
public class DeleteReminderCommand extends Command {

    public static final String COMMAND_WORD = "forget";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a reminder from an existing contact, "
            + "as specified by the index number used in the displayed contact list.\n\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_DELETE_REMINDER + "REMINDER_INDEX\n\n"
            + "Example: " + COMMAND_WORD + " 2 "
            + PREFIX_DELETE_REMINDER + "1";
    public static final String MESSAGE_DELETE_REMINDER_SUCCESS = "Deleted reminder number %1$s for %2$s.";
    public static final String MESSAGE_EMPTY_REMINDER_LIST = "There is no reminder.";
    public static final String MESSAGE_INVALID_REMINDER_INDEX = "Invalid index for reminder!";

    private final Index index;
    private final Index reminderIndex;

    /**
     * Creates an DeleteReminderCommand to delete the specified {@code Reminder} using the {@code Index} from the
     * specified {@code Person}
     *
     * @param index of the person
     * @param reminderIndex to be deleted.
     */
    public DeleteReminderCommand(Index index, Index reminderIndex) {
        requireNonNull(index);
        requireNonNull(reminderIndex);
        this.index = index;
        this.reminderIndex = reminderIndex;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToDelete = model.getFilteredPerson(index);
        ReminderList reminderList = personToDelete.getReminderList();

        if (reminderList.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_EMPTY_REMINDER_LIST));
        }

        if (reminderIndex.getOneBased() > personToDelete.getReminderListSize()) {
            throw new CommandException(String.format(MESSAGE_INVALID_REMINDER_INDEX));
        }

        ReminderList updatedReminderList = reminderList.delete(reminderIndex);
        Person updatedPerson = createEditedPerson(personToDelete, updatedReminderList);

        model.setPerson(personToDelete, updatedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_DELETE_REMINDER_SUCCESS, reminderIndex.getOneBased(),
                personToDelete.getName()));
    }

    /**
     * Creates a {@code Person} with the details of {@code personToDelete}
     * edited with {@code ReminderList}.
     *
     * @return the modified {@code Person}
     */
    private static Person createEditedPerson(Person personToDelete, ReminderList reminderList) {
        requireNonNull(personToDelete);

        Name updatedName = personToDelete.getName();
        Phone updatedPhone = personToDelete.getPhone();
        Email updatedEmail = personToDelete.getEmail();
        Address updatedAddress = personToDelete.getAddress();
        BirthDate updatedBirthDate = personToDelete.getBirthDate();
        List<ContactedInfo> updatedContactedInfo = personToDelete.getContactedInfoList();
        Set<Tag> updatedTags = personToDelete.getTags();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedBirthDate, updatedContactedInfo, updatedTags, reminderList);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DeleteReminderCommand)) {
            return false;
        }

        DeleteReminderCommand deleteReminderCommand = (DeleteReminderCommand) other;
        return this.index.equals(deleteReminderCommand.index)
                && this.reminderIndex.equals(((DeleteReminderCommand) other).reminderIndex);
    }
}
