package seedu.address.model.reminder;

import seedu.address.model.date.ReminderDate;

import java.util.PriorityQueue;

/**
 * Represents a list of reminders.
 * The list is ordered by ReminderDate, in the event where two or more reminders share the same ReminderDate, they will
 * be sorted according to their ReminderDescription.
 */
public class ReminderList {

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
     */
    public ReminderList(ReminderList reminderList) {
        this.reminderPriorityQueue = new PriorityQueue<>(reminderList.reminderPriorityQueue);
    }

    /**
     * Adds a Reminder object to the current list.
     * @param reminder to be added.
     * @return the updated ReminderList
     */
    public ReminderList add(Reminder reminder) {
        this.reminderPriorityQueue.add(reminder);
        return this;
    }

    /**
     * Deletes a Reminder object from the current list.
     * @param reminder to be deleted.
     * @return the updated ReminderList
     */
    public ReminderList delete(Reminder reminder) {
        this.reminderPriorityQueue.remove(reminder);
        return this;
    }

    /**
     * Finds a Reminder object to the current list.
     * @param reminderDescription to search.
     * @return the Reminder found. If there is no matching searches, returns null.
     */
    public Reminder find(ReminderDescription reminderDescription) {
        Reminder reminderFound = null;
        PriorityQueue<Reminder> reminderListCopy = new PriorityQueue<Reminder>(this.reminderPriorityQueue);

        while (!reminderListCopy.isEmpty()) {
            reminderFound = reminderListCopy.poll();
            if (reminderFound.getDescription().equals(reminderDescription)) {
                break;
            }
        }

        if (reminderFound.getDescription().equals(reminderDescription)) {
            return reminderFound;
        } else {
            return null;
        }
    }

    /**
     * Finds all the Reminder objects with the same ReminderDate as the parameter.
     * @param reminderDate to check for
     * @return a ReminderList with Reminder objects happening on the same date as the provided date.
     */
    public ReminderList sameDateAs(ReminderDate reminderDate) {
        ReminderList newReminderList = new ReminderList(this);
        newReminderList.reminderPriorityQueue.stream().filter(reminder -> reminder.isSameDateAs(reminderDate));
        return newReminderList;
    }

    public PriorityQueue<Reminder> getPriorityQueue() {
        return this.reminderPriorityQueue;
    }
}
