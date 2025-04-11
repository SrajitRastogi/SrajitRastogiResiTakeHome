package com.Srajit.exercise.util;
import org.junit.jupiter.api.Test;

import com.Srajit.exercise.utils.DateParser;

import static org.junit.jupiter.api.Assertions.*;

public class DateParserTest {
    @Test
    void shouldParseValidDateFormats() {
        assertEquals("2017-02-27", DateParser.parseDate("02/27/17"));
        assertEquals("2018-06-02", DateParser.parseDate("June 2, 2018"));
        assertEquals("2016-07-13", DateParser.parseDate("Jul-13-2016"));
    }

    @Test
    void shouldReturnNullForInvalidDate() {
        assertNull(DateParser.parseDate("April 31, 2018")); // Invalid date
        assertNull(DateParser.parseDate("Not a date"));
    }
}
