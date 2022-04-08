package seedu.address.model.date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ReminderDateTest {

    @Test
    public void parse_validInput_success() {
        assertEquals("01 Jan 2020", ReminderDate.parse("2020-01-01").toString());
    }

    @Test
    public void parse_invalidInput_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> ReminderDate.parse("test"));
    }

    @Test
    public void compareTo_validInput_success() {
        ReminderDate reminderDateFirst = new ReminderDate(LocalDate.of(2020, 1, 2));
        ReminderDate reminderDateSecond = new ReminderDate(LocalDate.of(2020, 2, 1));
        ReminderDate reminderDateThird = new ReminderDate(LocalDate.of(2022, 1, 1));

        // different dates
        assertTrue(reminderDateFirst.compareTo(reminderDateSecond) < 0);
        assertTrue(reminderDateSecond.compareTo(reminderDateThird) < 0);
        assertTrue(reminderDateFirst.compareTo(reminderDateThird) < 0);

        assertTrue(reminderDateThird.compareTo(reminderDateSecond) > 0);
        assertTrue(reminderDateThird.compareTo(reminderDateFirst) > 0);
        assertTrue(reminderDateSecond.compareTo(reminderDateFirst) > 0);

        // same dates
        assertEquals(0, reminderDateFirst.compareTo(reminderDateFirst));
        assertEquals(0, reminderDateSecond.compareTo(reminderDateSecond));
        assertEquals(0, reminderDateThird.compareTo(reminderDateThird));
    }

    @Test
    public void compareTo_nullValue_throwsNullPointerException() {
        ReminderDate reminderDate = new ReminderDate(LocalDate.of(2020, 1, 1));
        assertThrows(NullPointerException.class, () -> reminderDate.compareTo(null));
    }

    @Test
    public void equals() {
        ReminderDate reminderDateFirst = new ReminderDate(LocalDate.of(2021, 1, 2));
        ReminderDate reminderDateSecond = new ReminderDate(LocalDate.of(2021, 2, 1));
        ReminderDate reminderDateThird = new ReminderDate(LocalDate.of(2022, 1, 1));

        // compared with itself
        assertTrue(reminderDateFirst.equals(reminderDateFirst));

        // compared with a copy of itself
        ReminderDate reminderDateFirstCopy = new ReminderDate(LocalDate.of(2021, 1, 2));
        assertTrue(reminderDateFirst.equals(reminderDateFirstCopy));

        // compared with a different type
        assertFalse(reminderDateFirst.equals(1));

        // compared with null
        assertFalse(reminderDateFirst.equals(null));

        // compared with same type but different values
        assertFalse(reminderDateFirst.equals(reminderDateSecond));
        assertFalse(reminderDateFirst.equals(reminderDateThird));
        assertFalse(reminderDateSecond.equals(reminderDateThird));
    }
}
