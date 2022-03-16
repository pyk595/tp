package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMINDER_DESCRIPTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.date.BirthDate;
import seedu.address.model.date.DocumentedDate;
import seedu.address.model.date.RecentDate;
import seedu.address.model.person.Address;
import seedu.address.model.person.Description;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.reminder.ReminderList;
import seedu.address.model.tag.Tag;

/**
 * Adds a reminder to a contact.
 */
public class AddReminderCommand extends Command {

    public static final String COMMAND_WORD = "remind";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a reminder to an existing contact, "
            + "as specified by the index number used in the displayed person list. The reminder will be replace "
            + "any existing reminder. If a date is not specified, it will register today as the reminder date.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_REMINDER_DESCRIPTION + "REMINDER "
            + "[" + PREFIX_REMINDER_DATE + "DATE]\n"
            + "Example: " + COMMAND_WORD + " 2 "
            + PREFIX_REMINDER_DESCRIPTION + "meeting "
            + PREFIX_REMINDER_DATE + new DocumentedDate(LocalDate.of(2022, 01, 01)).toString();
    public static final String MESSAGE_ADD_REMINDER_SUCCESS = "Added reminder: %1$s";

    private final Index index;
    private final Reminder reminder;

    /**
     * Constructs an {@code AddReminderCommand} with the given {@code Index} and {@code Reminder}.
     *
     * @param index    of the person in the filtered person list to add the {@code Reminder}.
     * @param reminder to be added to the {@code Person} specified by {@code index}.
     */
    public AddReminderCommand(Index index, Reminder reminder) {
        requireNonNull(index);
        requireNonNull(reminder);

        this.index = index;
        this.reminder = reminder;
    }

    /**
     * Creates and returns a {@code Person} with the details of personToEdit {@code Person}
     * added with reminderToAdd {@code Reminder}.
     */
    private static Person createPersonWithAddedReminder(Person personToEdit, Reminder reminderToAdd) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        BirthDate updatedBirthDate = personToEdit.getBirthDate();
        RecentDate updatedContactedDate = personToEdit.getContactedDate();
        Description updatedContactedDescription = personToEdit.getContactedDesc();
        Set<Tag> updatedTags = personToEdit.getTags();
        ReminderList updatedReminders = personToEdit.getReminderList();
        updatedReminders.add(reminderToAdd);

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedBirthDate,
                updatedContactedDate, updatedContactedDescription, updatedTags, updatedReminders);
    }

    /**
     * Adds a reminder to an existing person in the address book, if the reminder does not exist.
     * If there is an existing reminder, the newly added reminder will replace the previous one.
     *
     * @param model {@code Model} which the command should operate on.
     * @return the command result after the command execution.
     * @throws CommandException if the {@code Reminder} already exists or the {@code Index} is invalid.
     */
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());

        Person editedPerson = createPersonWithAddedReminder(personToEdit, this.reminder);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_ADD_REMINDER_SUCCESS, editedPerson));
    }
}
