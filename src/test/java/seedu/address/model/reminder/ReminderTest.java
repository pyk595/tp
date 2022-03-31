package seedu.address.model.reminder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.date.ReminderDate;

public class ReminderTest {

    @Test
    public void constructor_anyFieldsNull_throwsNullPointerException() {
        ReminderDescription validDescription = new ReminderDescription("valid");
        ReminderDate validDate = new ReminderDate(LocalDate.of(2022, 1, 1));

        // Reminder description is null
        assertThrows(NullPointerException.class, () -> new Reminder(null, validDate));

        // Reminder date is null
        assertThrows(NullPointerException.class, () -> new Reminder(validDescription, null));

        // Both reminder date and description are null
        assertThrows(NullPointerException.class, () -> new Reminder(null, null));
    }

    @Test
    public void isSameDateAs_validDates_success() {
        Reminder firstReminder = new Reminder(new ReminderDescription("first"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));

        // Same date
        assertTrue(firstReminder.isSameDateAs(new ReminderDate(LocalDate.of(2022, 1, 1))));

        // Different year
        assertFalse(firstReminder.isSameDateAs(new ReminderDate(LocalDate.of(2021, 1, 1))));

        // Different month
        assertFalse(firstReminder.isSameDateAs(new ReminderDate(LocalDate.of(2022, 2, 1))));

        // Different day
        assertFalse(firstReminder.isSameDateAs(new ReminderDate(LocalDate.of(2022, 1, 2))));
    }

    @Test
    public void compareTo_validDates_success() {
        Reminder firstReminder = new Reminder(new ReminderDescription("1st"),
                new ReminderDate(LocalDate.of(2020, 1, 1)));
        Reminder secondReminder = new Reminder(new ReminderDescription("2nd"),
                new ReminderDate(LocalDate.of(2021, 1, 1)));
        Reminder thirdReminder = new Reminder(new ReminderDescription("3rd"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));
        Reminder fourthReminder = new Reminder(new ReminderDescription("4th"),
                new ReminderDate(LocalDate.of(2022, 1, 1)));

        // Different dates and descriptions
        assertTrue(firstReminder.compareTo(secondReminder) < 0);
        assertTrue(firstReminder.compareTo(thirdReminder) < 0);
        assertTrue(firstReminder.compareTo(fourthReminder) < 0);
        assertTrue(secondReminder.compareTo(thirdReminder) < 0);
        assertTrue(secondReminder.compareTo(fourthReminder) < 0);

        assertTrue(fourthReminder.compareTo(secondReminder) > 0);
        assertTrue(fourthReminder.compareTo(firstReminder) > 0);
        assertTrue(thirdReminder.compareTo(secondReminder) > 0);
        assertTrue(thirdReminder.compareTo(firstReminder) > 0);
        assertTrue(secondReminder.compareTo(firstReminder) > 0);

        // Same date and different descriptions
        assertEquals(-1, thirdReminder.compareTo(fourthReminder));
        assertEquals(1, fourthReminder.compareTo(thirdReminder));

        // Same date and descriptions
        assertEquals(0, firstReminder.compareTo(firstReminder));
        assertEquals(0, secondReminder.compareTo(secondReminder));
        assertEquals(0, thirdReminder.compareTo(thirdReminder));
        assertEquals(0, fourthReminder.compareTo(fourthReminder));
    }

    @Test
    public void equals() {
        ReminderDescription reminderDescriptionFirst = new ReminderDescription("first");
        ReminderDescription reminderDescriptionSecond = new ReminderDescription("second");

        ReminderDate reminderDateFirst = new ReminderDate(LocalDate.of(2021, 1, 1));
        ReminderDate reminderDateSecond = new ReminderDate(LocalDate.of(2022, 2, 2));

        Reminder reminderFirst = new Reminder(reminderDescriptionFirst, reminderDateFirst);
        Reminder reminderSecond = new Reminder(reminderDescriptionFirst, reminderDateSecond);
        Reminder reminderThird = new Reminder(reminderDescriptionSecond, reminderDateFirst);
        Reminder reminderFourth = new Reminder(reminderDescriptionSecond, reminderDateSecond);

        // compared with itself
        assertTrue(reminderFirst.equals(reminderFirst));

        // compared with a copy of itself
        Reminder reminderFirstCopy = new Reminder(reminderDescriptionFirst, reminderDateFirst);
        assertTrue(reminderFirst.equals(reminderFirstCopy));

        // compared with a different type
        assertFalse(reminderFirst.equals(1));

        // compared with null
        assertFalse(reminderFirst.equals(null));

        // compared with same type but different values
        assertFalse(reminderFirst.equals(reminderSecond));
        assertFalse(reminderFirst.equals(reminderThird));
        assertFalse(reminderFirst.equals(reminderFourth));
        assertFalse(reminderSecond.equals(reminderThird));
        assertFalse(reminderSecond.equals(reminderFourth));
        assertFalse(reminderThird.equals(reminderFourth));
    }
}
