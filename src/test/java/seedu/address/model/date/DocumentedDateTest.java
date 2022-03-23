package seedu.address.model.date;

import static java.time.temporal.ChronoUnit.DAYS;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class DocumentedDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DocumentedDate(null));
    }

    @Test
    public void getDaysPassed_validTestDate_success() {
        String dateString = "2022-01-01";
        LocalDate testDate = LocalDate.parse(dateString);
        LocalDate today = LocalDate.now();
        int days = (int) DAYS.between(testDate, today);
        DocumentedDate date = new DocumentedDate(testDate);
        assertEquals(days, date.getDaysPassed());
    }

    @Test
    public void isToday_currentDate_returnsTrue() {
        LocalDate today = LocalDate.now();
        DocumentedDate date = new DocumentedDate(today);
        assertTrue(date.isToday());
    }

    @Test
    public void isToday_differentDate_returnsFalse() {
        DocumentedDate date = new DocumentedDate(LocalDate.parse("2022-01-01"));
        assertFalse(date.isToday());
    }

    @Test
    public void isValidDate_validDate_returnsTrue() {
        assertTrue(DocumentedDate.isValidDate("2000-01-01"));
    }

    @Test
    public void isValidDate_invalidDate_returnsFalse() {
        assertFalse(DocumentedDate.isValidDate("2000/01/01"));
    }

    @Test
    public void isValidDate_isoFormattedDate_returnsFalse() {
        assertFalse(DocumentedDate.isValidDate("01 JANUARY 2020"));
    }

    @Test
    public void isValidDate_negativeDatePrefix_returnsFalse() {
        assertFalse(DocumentedDate.isValidDate("-2000-01-01"));
    }

    @Test
    public void isValidDate_positiveDatePrefix_returnsFalse() {
        assertFalse(DocumentedDate.isValidDate("+2000-01-01"));
    }

    @Test
    public void isValidDate_invalidYearLength_returnsFalse() {
        assertFalse(DocumentedDate.isValidDate("200000-01-01"));
    }

    @Test
    public void isValidDate_validYearLengthPositive_returnsFalse() {
        assertFalse(DocumentedDate.isValidDate("+200000-01-01"));
    }

    @Test
    public void isValidDate_validYearLengthNegative_returnsFalse() {
        assertFalse(DocumentedDate.isValidDate("-200000-01-01"));
    }

    @Test
    public void testToString_standardDate_success() {
        DocumentedDate date = new DocumentedDate(LocalDate.parse("2022-01-01"));
        assertEquals("01 Jan 2022", date.toString());
    }
}
