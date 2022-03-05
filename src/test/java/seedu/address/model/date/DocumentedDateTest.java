package seedu.address.model.date;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import seedu.address.commons.exceptions.IllegalValueException;

class DocumentedDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DocumentedDate(null));
    }

    @Test
    public void getDaysPassed_validTestDate_success() throws IllegalValueException {
        String dateString = "2022-01-01";
        LocalDate testDate = LocalDate.parse(dateString);
        LocalDate today = LocalDate.now();
        int days = (int) DAYS.between(testDate, today);
        DocumentedDate date = new DocumentedDate(testDate);
        assertEquals(days, date.getDaysPassed());
    }

    @Test
    public void isToday_currentDate_returnsTrue() throws IllegalValueException {
        LocalDate today = LocalDate.now();
        DocumentedDate date = new DocumentedDate(today);
        assertTrue(date.isToday());
    }

    @Test
    public void isToday_differentDate_returnsFalse() throws IllegalValueException {
        DocumentedDate date = new DocumentedDate(LocalDate.parse("2022-01-01"));
        assertFalse(date.isToday());
    }

    @Test
    public void testToString_standardDate_success() throws IllegalValueException {
        DocumentedDate date = new DocumentedDate(LocalDate.parse("2022-01-01"));
        assertEquals("1 JANUARY 2022", date.toString());
    }
}
