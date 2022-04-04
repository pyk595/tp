package seedu.address.model.date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class RecentDateTest {
    private static final DateTimeFormatter FORMATTER_INPUT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private LocalDate test;
    private LocalDate tomorrow;


    @BeforeEach
    public void setUp() {
        test = LocalDate.now();
        tomorrow = LocalDate.now().plusDays(1);
    }
    @Test
    public void parse_validString_success() {
        assertEquals("01 Jan 2020", RecentDate.parse("2020-01-01").toString());
    }

    @Test
    public void parse_invalidString_failure() {
        assertThrows(IllegalArgumentException.class, ()->RecentDate.parse("hello"));
    }

    @Test
    public void equals() {
        RecentDate testRecentDate = new RecentDate(test);
        RecentDate nextDay = new RecentDate(tomorrow);
        String testString = test.format(FORMATTER_INPUT);
        RecentDate alternate = RecentDate.parse(testString);
        assertEquals(testRecentDate, alternate);

        assertTrue(testRecentDate.equals(testRecentDate));
        assertTrue(testRecentDate.equals(alternate));
        assertFalse(testRecentDate.equals(1));
        assertFalse(testRecentDate.equals(null));
        assertFalse(testRecentDate.equals(nextDay));
    }
}
