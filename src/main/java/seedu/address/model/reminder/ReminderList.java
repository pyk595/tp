package seedu.address.model.reminder;

import java.util.PriorityQueue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.date.ReminderDate;

/**
 * Represents a list of reminders.
 * The list is ordered by ReminderDate, in the event where two or more reminders share the same ReminderDate, they will
 * be sorted according to their ReminderDescription.
 */
public class ReminderList {

    public static final String MESSAGE_DUPLICATE_REMINDER = "This person already has a reminder %s";
    private final PriorityQueue<Reminder> reminderPriorityQueue;

    /**
     * Constructs an empty ReminderList.
     */
    public ReminderList() {
        this.reminderPriorityQueue = new PriorityQueue<>();
    }

    /**
     * Constructs a ReminderList with items provided by the parameter.
     * The main purpose is to clone the original ReminderList.
     *
     * @param reminderList which provides the reminders to be copied.
     */
    public ReminderList(ReminderList reminderList) {
        this.reminderPriorityQueue = new PriorityQueue<>(reminderList.reminderPriorityQueue);
    }

    /**
     * Returns a copy of the current {@code ReminderList}.
     */
    public ReminderList getCopy() {
        return new ReminderList(this);
    }

    /**
     * Checks if a given {@code Reminder} exists in the {@code ReminderList}.
     *
     * @param reminder to be checked
     * @return true if the reminder exists, false otherwise.
     */
    public boolean containsReminder(Reminder reminder) {
        return this.reminderPriorityQueue.contains(reminder);
    }

    /**
     * Adds a Reminder object to the current list.
     *
     * @param reminder to be added.
     */
    public void add(Reminder reminder) {
        if (reminderPriorityQueue.contains(reminder)) {
            return;
        }

        this.reminderPriorityQueue.add(reminder);
    }

    /**
     * Deletes a Reminder object from the current list.
     *
     * @param reminder to be deleted.
     * @return the updated ReminderList
     */
    public ReminderList delete(Reminder reminder) {
        this.reminderPriorityQueue.remove(reminder);
        return this;
    }

    /**
     * Finds a Reminder object to the current list.
     *
     * @param reminderDescription to search.
     * @param reminderDate
     * @return the Reminder found. If there is no matching searches, returns null.
     */
    public Reminder find(ReminderDescription reminderDescription, ReminderDate reminderDate)
            throws IllegalValueException {
        Reminder reminderFound = null;
        PriorityQueue<Reminder> reminderListCopy = new PriorityQueue<Reminder>(this.reminderPriorityQueue);

        while (!reminderListCopy.isEmpty()) {
            reminderFound = reminderListCopy.poll();
            if (reminderFound.getDescription().equals(reminderDescription)
                    && reminderFound.getReminderDate().equals(reminderDate)) {
                break;
            }
        }

        if (reminderFound.getDescription().equals(reminderDescription)
                && reminderFound.getReminderDate().equals(reminderDate)) {
            return reminderFound;
        } else {
            throw new IllegalValueException(String.format("There is no reminder %1$s happening on %2$s.",
                    reminderDescription, reminderDate));
        }
    }

    /**
     * Finds all the Reminder objects with the same ReminderDate as the parameter.
     *
     * @param reminderDate to check for
     * @return a ReminderList with Reminder objects happening on the same date as the provided date.
     */
    public ReminderList sameDateAs(ReminderDate reminderDate) {
        ReminderList newReminderList = new ReminderList();
        reminderPriorityQueue.forEach(reminder -> {
            if (reminder.isSameDateAs(reminderDate)) {
                newReminderList.add(reminder);
            }
        });
        return newReminderList;
    }

    public PriorityQueue<Reminder> getPriorityQueue() {
        return this.reminderPriorityQueue;
    }

    /**
     * Converts the given {@code ReminderList} to output format
     */
    public String toOutputFormat() {
        StringBuilder output = new StringBuilder();
        int counter = 1;
        for (Reminder reminder : reminderPriorityQueue) {
            output.append(String.format("%1$d. %2$s\n", counter, reminder.toString()));
            counter++;
        }
        return output.toString();
    }
}
