package seedu.address.model.date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;



class BirthDateTest {

    @Test
    public void of_validString_success() {
        assertEquals("1 JANUARY 2020", BirthDate.of("2020-01-01").toString());
    }

    @Test
    public void of_invalidString_failure() {
        assertThrows(DateTimeParseException.class, ()->BirthDate.of("hello"));
    }

    @Test
    public void isToday_currentDate_returnsTrue() {
        BirthDate test = new BirthDate(LocalDate.now());
        assertTrue(test.isToday());
    }

    @Test
    public void isToday_currentDateDifferentYear_returnsTrue() {
        LocalDate today = LocalDate.now();
        BirthDate test = new BirthDate(today.withYear(1990));
        assertTrue(test.isToday());
    }

    @Test
    public void isToday_differentDate_returnsFalse() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        BirthDate test = new BirthDate(yesterday);
        assertFalse(test.isToday());
    }

    @Test
    public void isToday_differentDateDifferentYear_returnsFalse() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        BirthDate test = new BirthDate(yesterday.withYear(1990));
        assertFalse(test.isToday());
    }
}
