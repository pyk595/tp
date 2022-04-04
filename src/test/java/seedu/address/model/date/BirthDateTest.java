package seedu.address.model.date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;



class BirthDateTest {
    private static final DateTimeFormatter FORMATTER_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test
    public void parse_validString_success() {
        assertEquals("01 Jan 2020", BirthDate.parse("2020-01-01").toString());
    }

    @Test
    public void parse_invalidString_failure() {
        assertThrows(IllegalArgumentException.class, ()->BirthDate.parse("hello"));
    }

    @Test
    public void equals() {
        LocalDate test = LocalDate.now();
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        BirthDate testBirthDate = new BirthDate(test);
        BirthDate nextDay = new BirthDate(tomorrow);
        String testString = test.format(FORMATTER_INPUT);
        BirthDate alternate = BirthDate.parse(testString);
        assertEquals(testBirthDate, alternate);

        assertTrue(testBirthDate.equals(testBirthDate));
        assertTrue(testBirthDate.equals(alternate));
        assertFalse(testBirthDate.equals(1));
        assertFalse(testBirthDate.equals(null));
        assertFalse(testBirthDate.equals(nextDay));
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
