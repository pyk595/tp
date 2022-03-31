package seedu.address.model.reminder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ReminderDescriptionTest {

    @Test
    public void constructor_null_throwNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ReminderDescription(null));
    }

    @Test
    public void constructor_invalidReminderDescription_throwsIllegalArgumentException() {
        String invalidReminderDescription = "invalid!";
        assertThrows(IllegalArgumentException.class, () -> new ReminderDescription(invalidReminderDescription));
    }

    @Test
    public void isValidDescription_validInputs_success() {
        // valid reminder description
        String validReminderDescription = "valid";
        assertTrue(ReminderDescription.isValidDescription(validReminderDescription));

        // invalid reminde description
        String invalidReminderDescription = "invalid!";
        assertFalse(ReminderDescription.isValidDescription(invalidReminderDescription));
    }

    @Test
    public void equals() {
        String first = "first";
        String second = "second";

        ReminderDescription reminderDescriptionFirst = new ReminderDescription(first);
        ReminderDescription reminderDescriptionSecond = new ReminderDescription(second);

        // compared with itself
        assertTrue(reminderDescriptionFirst.equals(reminderDescriptionFirst));

        // compared with a copy of itself
        ReminderDescription reminderDescriptionFirstCopy = new ReminderDescription(first);
        assertTrue(reminderDescriptionFirst.equals(reminderDescriptionFirstCopy));

        // compared with a different type
        assertFalse(reminderDescriptionFirst.equals(1));

        // compared with null
        assertFalse(reminderDescriptionFirst.equals(null));

        // compared with same type but different values
        assertFalse(reminderDescriptionFirst.equals(reminderDescriptionSecond));
    }

    @Test
    public void compareTo_validDescriptions_success() {
        ReminderDescription reminderDescriptionFirst = new ReminderDescription("first");
        ReminderDescription reminderDescriptionSecond = new ReminderDescription("second");

        // different values
        assertTrue(reminderDescriptionFirst.compareTo(reminderDescriptionSecond) < 0);
        assertTrue(reminderDescriptionSecond.compareTo(reminderDescriptionFirst) > 0);

        // same values
        assertEquals(0, reminderDescriptionFirst.compareTo(reminderDescriptionFirst));
        assertEquals(0, reminderDescriptionSecond.compareTo(reminderDescriptionSecond));
    }

    @Test
    public void equalsIgnoreCase_validDescriptions_success() {
        ReminderDescription reminderDescriptionFirst = new ReminderDescription("FIRST");
        ReminderDescription reminderDescriptionSecond = new ReminderDescription("first");
        ReminderDescription reminderDescriptionThird = new ReminderDescription("FiRsT");

        // same upper and lower casing
        assertTrue(reminderDescriptionFirst.equals(reminderDescriptionFirst));
        assertTrue(reminderDescriptionSecond.equals(reminderDescriptionSecond));
        assertTrue(reminderDescriptionThird.equals(reminderDescriptionThird));

        // different upper and lower casing
        assertTrue(reminderDescriptionFirst.equals(reminderDescriptionSecond));
        assertTrue(reminderDescriptionFirst.equals(reminderDescriptionThird));
        assertTrue(reminderDescriptionSecond.equals(reminderDescriptionThird));
    }
}
