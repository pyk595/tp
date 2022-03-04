package seedu.address.model.date;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void getDaysPassed_validTestDate_success() {
        String dateString = "2022-01-01";
        LocalDate testDate = LocalDate.parse(dateString);
        LocalDate today = LocalDate.now();
        int days = (int) DAYS.between(testDate, today);
        Date date = new Date(dateString);
        assertEquals(days, date.getDaysPassed());
    }

    @Test
    public void isToday_returnsTrue() {
        LocalDate today = LocalDate.now();
        Date date = new Date(today.toString());
        assertTrue(date.isToday());
    }

    @Test
    public void isToday_returnsFalse() {
        Date date = new Date("2022-01-01");
        assertFalse(date.isToday());
    }

    @Test
    public void testToString_success() {
        Date date = new Date("2022-01-01");
        assertEquals("1 JANUARY 2022", date.toString());
    }
}
