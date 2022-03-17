package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.date.RecentDate;
import seedu.address.model.person.Description;
import seedu.address.model.person.Person;

/**
 * Changes the recent date of an existing person in the address book.
 */
public class ContactedCommand extends Command {

    public static final String MESSAGE_ADD_CONTACTEDINFO_SUCCESS = "Added Contacted Info to Person: %1$s";
    public static final String MESSAGE_DELETE_CONTACTEDINFO_SUCCESS = "Removed Contacted Info from Person: %1$s";

    public static final String COMMAND_WORD = "contacted";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the last contacted date  "
            + "by the index number used in the last person listing. "
            + "Existing date will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "d/ [DATE] "
            + "des/ [DESCRIPTION]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "d/ 2020-02-02 "
            + "des/ meetup.";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Last contacted date: %2$s, Description: %3$s";

    private final Index index;
    private final RecentDate date;
    private final Description description;

    /**
     * Creates an ContactedCommand to add the specified {@code Person}
     *
     * @param index of the person in the filtered person list to edit the contacted date
     * @param date of the person to be updated to
     * @param description of the contacted date
     */
    public ContactedCommand(Index index, RecentDate date, Description description) {
        requireAllNonNull(index, date, description);

        this.index = index;
        this.date = date;
        this.description = description;
    }

    /**
     * Creates a CommandResult object.
     *
     * @param model {@code Model} which the command should operate on.
     * @return the CommandResult object.
     * @throws CommandException if it is an invalid command.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getBirthDate(), date, description, personToEdit.getTags(),
                personToEdit.getReminderList());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ContactedCommand)) {
            return false;
        }

        // state check
        ContactedCommand e = (ContactedCommand) other;
        return index.equals(e.index)
                && date.equals(e.date)
                && description.equals(e.description);
    }

    /**
     * Generates a command execution success message based on whether
     * the contacted informaiton is added to or removed from
     * {@code personToEdit}.
     *
     * returns success message
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !date.value.isEmpty() ? MESSAGE_ADD_CONTACTEDINFO_SUCCESS
                : MESSAGE_DELETE_CONTACTEDINFO_SUCCESS;
        return String.format(message, personToEdit);
    }
}

