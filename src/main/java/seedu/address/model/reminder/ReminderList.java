package seedu.address.model.reminder;

import java.util.PriorityQueue;

import seedu.address.commons.core.index.Index;
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
     * @param index of the {@code Reminder}to be deleted.
     * @return the updated ReminderList
     */
    public ReminderList delete(Index index) {
        Reminder reminder = get(index);
        this.reminderPriorityQueue.remove(reminder);
        return this;
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

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        int counter = 1;
        for (Reminder reminder : reminderPriorityQueue) {
            output.append(String.format("%1$d. %2$s\n", counter, reminder.toString()));
            counter++;
        }
        return output.toString();
    }

    /**
     * Checks if the {@code ReminderList} is empty.
     */
    public boolean isEmpty() {
        return reminderPriorityQueue.isEmpty();
    }

    /**
     * Gets the size of the {@code ReminderList}
     */
    public int getSize() {
        return reminderPriorityQueue.size();
    }

    /**
     * Retrieves the {@code Reminder} based on the {@code Index} given.
     *
     * @param reminderIndex the index of the {@code reminder}
     * @return the {@code reminder} object
     */
    public Reminder get(Index reminderIndex) {
        int count = reminderIndex.getOneBased();
        ReminderList copy = getCopy();
        Reminder reminder = null;

        while (count != 0) {
            reminder = copy.reminderPriorityQueue.poll();
            count--;
        }

        return reminder;
    }
}
