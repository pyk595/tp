package seedu.address.model.reminder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.date.ReminderDate;

public class ReminderListTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ReminderList(null));
    }

    @Test
    public void getCopy_validReminderList_success() {
        ReminderList reminderListFirst = new ReminderList();
        ReminderList reminderListFirstCopy = reminderListFirst.getCopy();

        assertFalse(reminderListFirst.equals(reminderListFirstCopy));
    }

    @Test
    public void containsReminder_validReminder_success() {
        Reminder reminder = new Reminder(new ReminderDescription("test"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));
        Reminder otherReminder = new Reminder(new ReminderDescription("other test"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));


        ReminderList reminderList = new ReminderList();
        reminderList.add(reminder);

        assertTrue(reminderList.containsReminder(reminder));
        assertFalse(reminderList.containsReminder(otherReminder));

        // null values
        assertFalse(reminderList.containsReminder(null));
    }

    @Test
    public void add_validReminder_success() {
        Reminder reminder = new Reminder(new ReminderDescription("test"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));

        // reminderList does not contain reminder
        ReminderList reminderList = new ReminderList();
        assertFalse(reminderList.containsReminder(reminder));

        // reminderList contains reminder
        reminderList.add(reminder);
        assertTrue(reminderList.containsReminder(reminder));

        // does not add a duplicate
        reminderList.add(reminder);
        assertTrue(reminderList.containsReminder(reminder));
        assertFalse(reminderList.getPriorityQueue().size() > 1);
    }

    @Test
    public void delete_validReminder_success() {
        Index index = Index.fromOneBased(1);
        Reminder reminder = new Reminder(new ReminderDescription("test"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));

        ReminderList reminderList = new ReminderList();
        reminderList.add(reminder);
        assertTrue(reminderList.containsReminder(reminder));

        ReminderList deletedReminderList = reminderList.delete(index);
        assertTrue(reminderList.containsReminder(reminder));
        assertFalse(deletedReminderList.containsReminder(reminder));

        deletedReminderList = reminderList.delete(index);
        assertTrue(reminderList.containsReminder(reminder));
        assertFalse(deletedReminderList.containsReminder(reminder));
    }

    @Test
    public void sameDateAs_validReminderDate_success() {
        Reminder reminder = new Reminder(new ReminderDescription("test"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));

        ReminderList reminderList = new ReminderList();
        reminderList.add(reminder);
        assertTrue(reminderList.containsReminder(reminder));

        assertEquals(1, reminderList.getPriorityQueue().size());

        // there exists a reminder on the same date
        assertEquals(1, reminderList.sameDateAs(new ReminderDate(LocalDate.of(2022,
                1, 1))).getPriorityQueue().size());

        // there is no reminder on the same date
        assertTrue(reminderList.sameDateAs(new ReminderDate(LocalDate.of(2021,
                1, 1))).getPriorityQueue().isEmpty());
    }
}
